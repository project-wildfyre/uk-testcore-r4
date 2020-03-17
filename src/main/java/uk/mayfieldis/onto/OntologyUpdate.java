package uk.mayfieldis.onto;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.param.UriParam;
import ca.uhn.fhir.validation.ValidationOptions;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.dstu3.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.hl7.fhir.utilities.cache.ToolsVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@SpringBootApplication
public class OntologyUpdate implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(OntologyUpdate.class);

    FhirContext ctxSTU3 = FhirContext.forDstu3();

    FhirContext ctxR4 = FhirContext.forR4();

    String igLocation = "https://project-wildfyre.github.io/uk-testcore-r4i/";
    String ontoLocation ="https://ontoserver.dataproducts.nhs.uk/fhir/";

    String SNOMEDVERSION = "http://snomed.info/sct/999000031000000106/version/20190320";
    private IGenericClient client;

    String path = "output/";
    PackageCacheManager pcm;
    NpmPackage npm = null;

    private Map<String, CodeSystem> myCodeSystems;
    private Map<String, StructureDefinition> myStructureDefinitions;
    private Map<String, ValueSet> myValueSets;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(OntologyUpdate.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }

        this.client = ctxSTU3.newRestfulGenericClient(ontoLocation);
        this.client.setEncoding(EncodingEnum.XML);
        try {

            //this.IGValidationSupport(igLocation);
            this.IGValidationSupport("uk.testcore.r4","4.0.0");
           // this.fetchCore();
            this.UpdateOntoServer();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

    public void IGValidationSupport(String igPackage, String igVersion) throws Exception {
        pcm = new PackageCacheManager(true, ToolsVersion.TOOLS_VERSION);

        this.myCodeSystems = new HashMap();
        this.myValueSets = new HashMap<>();
        this.myStructureDefinitions = new HashMap<>();

        npm = pcm.loadPackage(igPackage, igVersion);
        //    for (String resource : npm.listResources("CodeSystem", "ConceptMap", "ImplementationGuide", "StructureMap", "ValueSet", "StructureDefinition")) {
        //        LOG.info(resource);
        //    }
        for (String resource : npm.listResources( "CodeSystem","ValueSet")) {

            IBaseResource base = ctxSTU3.newJsonParser().parseResource(npm.load("package", resource));
            if (base instanceof CodeSystem) {
                CodeSystem codeSystem = (CodeSystem) base;
                LOG.debug(codeSystem.getUrl());
                this.myCodeSystems.put(codeSystem.getUrl(),codeSystem);
            }
            if (base instanceof ValueSet) {
                ValueSet valueSet = (ValueSet) base;
                LOG.debug(valueSet.getUrl());
                this.myValueSets.put(valueSet.getUrl(),valueSet);
            }
        }

    }


    public void UpdateOntoServer() throws Exception {
        for (CodeSystem codeSystem : this.myCodeSystems.values()) {
            CodeSystem ontoCS = fetchCodeSystemCall(codeSystem.getUrl());
            if (ontoCS == null) {
                LOG.info("Missing {}",codeSystem.getUrl());
                updateOntoCodeSystem(codeSystem);
            }
        }
        for (ValueSet valueSet : this.myValueSets.values()) {
            ValueSet ontoVS = fetchValueSetCall(valueSet.getUrl());
            if (ontoVS == null) {
                LOG.info("Missing {}",valueSet.getUrl());
                updateOntoValueSet(valueSet);
            } else {
                if (valueSet.hasCompose() && valueSet.getCompose().hasInclude()
                && valueSet.getCompose().getIncludeFirstRep().getSystem().equals("http://snomed.info/sct")) {
                    expandValueSet(ontoVS, valueSet);
                }
            }
        }
    }

    public ValueSet.ValueSetExpansionComponent expandValueSet(ValueSet ontoVS, ValueSet valueSet) {

        LOG.debug("Expanding {}",valueSet.getUrl());

        try {
            ValueSet.ValueSetExpansionComponent expand = null;


            org.hl7.fhir.dstu3.model.ValueSet vsExpansion = null;


            vsExpansion = client
                    .operation()
                    .onType(org.hl7.fhir.dstu3.model.ValueSet.class)
                    .named("expand")
                    .withSearchParameter(org.hl7.fhir.dstu3.model.Parameters.class, "identifier", new UriParam(valueSet.getUrl()))
                    .returnResourceType(org.hl7.fhir.dstu3.model.ValueSet.class)
                    .useHttpGet()
                    .execute();

            if (vsExpansion != null) {

                LOG.debug("EXPANSION RETURNED");
                expand = vsExpansion.getExpansion();
                for (ValueSet.ValueSetExpansionContainsComponent containsComponent : expand.getContains()) {
                    containsComponent.setExtension(null);
                }
                valueSet.setExpansion(expand);
                String[] uri = valueSet.getUrl().split("/");
                String filename= uri[uri.length-1];

                org.hl7.fhir.r4.model.ValueSet output = VersionConvertor_30_40.convertValueSet(valueSet);

                String content = ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(output);
                LOG.debug(valueSet.getVersion());

                Files.writeString(Paths.get(path + "ValueSet-"+filename + ".xml"), content);
                return expand;
            }

        } catch (Exception ex) {
            LOG.error("Error in ValueSet {} NHSD Ontotology Server Returned {}",valueSet.getUrl(), ex.getMessage());
            //LOG.error(ex.getMessage());
        }
        return null;
    }

    public void updateOntoCodeSystem(CodeSystem cs) {
        cs.setId("");
        try {
            MethodOutcome method = client.create()
                    .resource(cs)
                    .conditional().where(CodeSystem.URL.matches().value(cs.getUrl()))
                    .execute();
            if (method.getCreated()) {
                LOG.info("Ontology server. Create CodeSystem " + cs.getUrl());

            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

    public void updateOntoValueSet(ValueSet vs) {
        LOG.info("Updating OntoServer " + vs.getUrl());
        try {
            MethodOutcome method = client.create()
                    .resource(vs)
                    .conditional().where(ValueSet.URL.matches().value(vs.getUrl()))
                    .execute();
            if (method.getCreated()) {
                LOG.info("Ontology server. Create ValueSet " + vs.getUrl());
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            //notSupportedValueSet.add(vs.getUrl());
        }
    }

    public void IGValidationSupport(String igUri) throws Exception {
        LOG.info("IG Validation Support Constructor");
        HttpClient client = getHttpClient();
        LOG.info("Retrieving Validate Pack from {}",igUri + "validate.pack");
        HttpGet request = new HttpGet(igUri + "validator.pack");

        this.myCodeSystems = new HashMap();
        this.myValueSets = new HashMap<>();
        this.myStructureDefinitions = new HashMap<>();

        getRequest(client,request);
    }



    private HttpClient getHttpClient(){
        final HttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient;
    }

    private void getRequest(HttpClient client1, HttpGet request) throws Exception {

        HttpResponse response;
        Reader reader;
        try {
            response = client1.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                LOG.info("Retrieved Validate Pack");
                ZipInputStream zis = new ZipInputStream(response.getEntity().getContent());
                ZipEntry zipEntry = zis.getNextEntry();
                while (zipEntry != null) {

                    if (zipEntry.getName().endsWith(".json")) {
                        reader = new InputStreamReader(zis);
                        IBaseResource resource = ctxSTU3.newJsonParser().parseResource(reader);
                        if (resource instanceof StructureDefinition) {
                            StructureDefinition sd = (StructureDefinition) resource;
                            //this.myStructureDefinitions.put(sd.getUrl(),sd);
                        } else if (resource instanceof CodeSystem) {
                            CodeSystem cs = (CodeSystem) resource;
                            this.myCodeSystems.put(cs.getUrl(),cs);
                        } else if (resource instanceof ValueSet) {
                            ValueSet vs = (ValueSet) resource;
                            this.myValueSets.put(vs.getUrl(),vs);
                        }
                        LOG.info(zipEntry.getName());
                        LOG.debug(ctxSTU3.newXmlParser().encodeResourceToString(resource));
                    }
                    zipEntry = zis.getNextEntry();
                }
                zis.closeEntry();
                zis.close();
            } else {
                LOG.error("Failed to retrieve validator pack: {} ", response.getStatusLine().getReasonPhrase());
                throw new Exception("Unable to load validation pack");
            }
        } catch (Exception e) {
            LOG.error(e.getStackTrace().toString());
            LOG.error(e.getMessage());
            throw e;
        }
    }

    private void fetchCore() {
            Map<String, CodeSystem> codeSystems;
            Map<String, ValueSet> valueSets;
            codeSystems = new HashMap();
            valueSets = new HashMap();
            this.loadCodeSystems( (Map)codeSystems, (Map)valueSets, "/org/hl7/fhir/dstu3/model/valueset/valuesets.xml");
            this.loadCodeSystems( (Map)codeSystems, (Map)valueSets, "/org/hl7/fhir/dstu3/model/valueset/v2-tables.xml");
            this.loadCodeSystems( (Map)codeSystems, (Map)valueSets, "/org/hl7/fhir/dstu3/model/valueset/v3-codesystems.xml");
            this.myCodeSystems.putAll(codeSystems);
        this.myValueSets.putAll(valueSets);
    }

    private ValueSet fetchValueSetCall( String uri) throws Exception {
        Bundle bundle = client.search().forResource(ValueSet.class).where(ValueSet.URL.matches().value(uri))
                .returnBundle(Bundle.class)
                .execute();
        if (bundle.hasEntry() && bundle.getEntry().size() > 1) LOG.error("Multiple ValueSets detected "+uri);
        if (bundle.hasEntry() && bundle.getEntryFirstRep().getResource() instanceof ValueSet) {
            LOG.debug("fetchValueSet OK {}} ",uri);
            return (ValueSet) bundle.getEntryFirstRep().getResource();
        } else {
            LOG.info("fetchValueSet MISSING {} ", uri);
        }

        return null;
    }
    private CodeSystem fetchCodeSystemCall( String uri) throws Exception {
        Bundle results = null;
        try {
            results = client.search().forResource(CodeSystem.class).where(ValueSet.URL.matches().value(uri))
                    .returnBundle(Bundle.class)
                    .execute();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
        if (results == null) return null;
        if (results.hasEntry() && results.getEntry().size() > 1) LOG.error("Multiple CodeSystems detected "+uri);
        if (results.hasEntry() && results.getEntryFirstRep().getResource() instanceof CodeSystem) {
            LOG.debug("fetchCodeSystem OK {}", uri);
            return (CodeSystem) results.getEntryFirstRep().getResource();
        } else {
            LOG.info("fetchCodeSystem MISSING {}", uri);
        }

        return null;
    }

    private void loadCodeSystems(Map<String, CodeSystem> theCodeSystems, Map<String, ValueSet> theValueSets, String theClasspath) {
        LOG.info("Loading CodeSystem/ValueSet from classpath: {}", theClasspath);
        InputStream inputStream = DefaultProfileValidationSupport.class.getResourceAsStream(theClasspath);
        InputStreamReader reader = null;
        if (inputStream != null) {
            try {
                reader = new InputStreamReader(inputStream, Charsets.UTF_8);
                Bundle bundle = (Bundle)ctxSTU3.newXmlParser().parseResource(Bundle.class, reader);
                Iterator var8 = bundle.getEntry().iterator();

                while(var8.hasNext()) {
                    Bundle.BundleEntryComponent next = (Bundle.BundleEntryComponent)var8.next();
                    String system;
                    if (next.getResource() instanceof CodeSystem) {
                        CodeSystem nextValueSet = (CodeSystem)next.getResource();
                        nextValueSet.getText().setDivAsString("");
                        system = nextValueSet.getUrl();
                        if (StringUtils.isNotBlank(system)) {
                            theCodeSystems.put(system, nextValueSet);
                        }
                    } else if (next.getResource() instanceof ValueSet) {
                        ValueSet nextValueSet = (ValueSet)next.getResource();
                        nextValueSet.getText().setDivAsString("");
                        system = nextValueSet.getUrl();
                        if (StringUtils.isNotBlank(system)) {
                            theValueSets.put(system, nextValueSet);
                        }
                    }
                }
            } finally {
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(inputStream);
            }
        } else {
            LOG.warn("Unable to load resource: {}", theClasspath);
        }

    }

}
