package uk.mayfieldis.onto;

import ca.uhn.fhir.context.FhirContext;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.commons.io.IOUtils;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IDomainResource;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@SpringBootApplication
public class IGUpdate implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(IGUpdate.class);

    FhirContext ctxSTU3 = FhirContext.forDstu3();

    FhirContext ctxR4 = FhirContext.forR4();

    String ontoLocation ="https://ontoserver.dataproducts.nhs.uk/fhir/";

    String SNOMEDVERSION = "http://snomed.info/sct/999000031000000106/version/20190320";
    private IGenericClient client;

    String outputpath = "input/";
    PackageCacheManager pcm;
    NpmPackage npm = null;

    private Map<String, org.hl7.fhir.r4.model.CodeSystem> myCodeSystems;
    private Map<String, org.hl7.fhir.r4.model.StructureDefinition> myStructureDefinitions;
    private Map<String, org.hl7.fhir.r4.model.ValueSet> myValueSets;
    private Map<String, org.hl7.fhir.r4.model.ConceptMap> myMaps;
    private Map<String, IDomainResource> myExamples;

    ImplementationGuide ig = null;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(IGUpdate.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        this.myCodeSystems = new HashMap();
        this.myValueSets = new HashMap<>();
        this.myStructureDefinitions = new HashMap<>();
        this.myMaps = new HashMap<>();
        this.myExamples = new HashMap<>();

        LOG.info("Extracting UK Core from simplifier");
        try {
            ig = (ImplementationGuide) ctxR4.newXmlParser().parseResource(Files.readString(Paths.get("input/resources/ImplementationGuide-UK-Core-R4.xml")));
            ig.getDefinition().setResource(new ArrayList<>());
            getUKCore();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
        LOG.info("Ontology Processing - using SNOMED ");
        this.client = ctxSTU3.newRestfulGenericClient(ontoLocation);
        try {
            this.updateResources();
          //  LOG.info(ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(ig));
            Files.writeString(Paths.get("input/resources/ImplementationGuide-UK-Core-R4.xml"),ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(ig));
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }

    }

    private void getUKCore() throws Exception {
        URL simplifier = new URL("https://simplifier.net/ukcore/$download?format=xml&filter=Resource");
        URLConnection yc = simplifier.openConnection();
        ZipInputStream zis = new ZipInputStream(yc.getInputStream());
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            LOG.info(zipEntry.getName());
            if (!zipEntry.isDirectory()) {

                IBaseResource base = ctxR4.newXmlParser().parseResource(convertZipInputStreamToInputStream(zis));
                if (base instanceof org.hl7.fhir.r4.model.CodeSystem) {
                    org.hl7.fhir.r4.model.CodeSystem codeSystem = (org.hl7.fhir.r4.model.CodeSystem) base;
                    codeSystem.setId(codeSystem.getId().replace("CodeSystem\\",""));
                    codeSystem.setId(codeSystem.getId().replace("CodeSystem/",""));
                    LOG.debug(codeSystem.getUrl());
                    this.myCodeSystems.put(codeSystem.getUrl(),codeSystem);
                } else
                if (base instanceof org.hl7.fhir.r4.model.ValueSet) {
                    org.hl7.fhir.r4.model.ValueSet valueSet = (org.hl7.fhir.r4.model.ValueSet) base;
                    valueSet.setId(valueSet.getId().replace("ValueSet\\",""));
                    valueSet.setId(valueSet.getId().replace("ValueSet/",""));
                    LOG.debug(valueSet.getUrl());
                    this.myValueSets.put(valueSet.getUrl(),valueSet);
                } else
                if (base instanceof org.hl7.fhir.r4.model.ConceptMap) {
                    org.hl7.fhir.r4.model.ConceptMap map = (org.hl7.fhir.r4.model.ConceptMap) base;
                    map.setId(map.getId().replace("ConceptMap\\",""));
                    map.setId(map.getId().replace("ConceptMap/",""));

                    LOG.debug(map.getUrl());
                    this.myMaps.put(map.getUrl(),map);
                }  else
                if (base instanceof org.hl7.fhir.r4.model.ImplementationGuide ||
                        base instanceof org.hl7.fhir.r4.model.Bundle) {
                    // Do nothing
                }
                else
                if (base instanceof org.hl7.fhir.r4.model.StructureDefinition) {

                    org.hl7.fhir.r4.model.StructureDefinition structureDefinition = (org.hl7.fhir.r4.model.StructureDefinition) base;
                    // Consent causing validation issues TODO
                    if (!structureDefinition.getId().contains("UKCore-Consent")) {
                        LOG.debug(structureDefinition.getUrl());
                        structureDefinition.setId(structureDefinition.getId().replace("StructureDefinition\\", ""));
                        structureDefinition.setId(structureDefinition.getId().replace("StructureDefinition/", ""));
                        this.myStructureDefinitions.put(structureDefinition.getUrl(), structureDefinition);
                    }
                }
                else if (base instanceof org.hl7.fhir.r4.model.DomainResource)
                {
                    DomainResource resource = (DomainResource) base;

                    resource.setId(resource.getId().replace(base.getClass().getSimpleName() + "/",""));
                    if (base instanceof ListResource) {
                        resource.setId(resource.getId().replace("List/", ""));
                        this.myExamples.put("List-" + resource.getId(), resource);
                    } else {
                        this.myExamples.put(base.getClass().getSimpleName() +"-" + resource.getId(), resource);
                    }

                }

            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    private InputStream convertZipInputStreamToInputStream(
            final ZipInputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(in, out);
        InputStream is = new ByteArrayInputStream(out.toByteArray());
        return is;
    }

    private void updateResources() {

        try {
            for (CodeSystem cs : myCodeSystems.values()) {
                Files.createDirectories(Paths.get(outputpath + "/resources/CodeSystem"));
                Path path = Paths.get(outputpath + "/resources/CodeSystem/"+ cs.getId() + ".xml");
                Files.writeString(path, ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(cs));
                ig.getDefinition().addResource()
                        .setName(cs.getName())
                        .setReference(new Reference("CodeSystem/"+cs.getId()));
            }
            for (org.hl7.fhir.r4.model.ValueSet vs : myValueSets.values()) {
                Files.createDirectories(Paths.get(outputpath + "/resources/ValueSet"));
                Path path = Paths.get(outputpath + "/resources/ValueSet/"+ vs.getId() + ".xml");
                Files.writeString(path, ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(vs));
                ig.getDefinition().addResource()
                        .setName(vs.getName())
                        .setReference(new Reference("ValueSet/"+vs.getId()));
            }
            for (org.hl7.fhir.r4.model.StructureDefinition sd : myStructureDefinitions.values()) {
                // TODO either UK Core or HAPI fault

                    Files.createDirectories(Paths.get(outputpath + "/resources/StructureDefinition"));
                    Path path = Paths.get(outputpath + "/resources/StructureDefinition/" + sd.getId() + ".xml");
                    Files.writeString(path, ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(sd));
                    ig.getDefinition().addResource()
                            .setName(sd.getName())
                            .setReference(new Reference("StructureDefinition/" + sd.getId()));

            }
            for (org.hl7.fhir.r4.model.ConceptMap map : myMaps.values()) {
                Files.createDirectories(Paths.get(outputpath + "/resources/ConceptMap"));
                Path path = Paths.get(outputpath + "/resources/ConceptMap/"+ map.getId() + ".xml");
                Files.writeString(path, ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(map));
                ig.getDefinition().addResource()
                        .setName(map.getName())
                        .setReference(new Reference("ConceptMap/"+map.getId()));
            }
            for (Map.Entry<String, IDomainResource> entry : myExamples.entrySet()) {
                Files.createDirectories(Paths.get(outputpath + "/Examples"));
                Path path = Paths.get(outputpath + "/Examples/"+ entry.getKey());
                Files.writeString(path, ctxR4.newXmlParser().setPrettyPrint(true).encodeResourceToString(entry.getValue()));
                String resourceName = entry.getValue().getClass().getSimpleName();
                if (resourceName.equals("ListResource")) resourceName="List";
                ig.getDefinition().addResource()
                        .setName(entry.getKey())
                        .setReference(new Reference(resourceName +"/" + entry.getValue().getId()))
                        .setExample(new BooleanType().setValue(true));
            }
        } catch (Exception ex) {
            LOG.error(ex.getClass().getSimpleName());
            LOG.error(ex.getMessage());

        }

    }





}
