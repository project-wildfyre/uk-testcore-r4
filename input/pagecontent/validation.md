This is a UK supplement to [HL7 FHIR Validating Resources](https://www.hl7.org/fhir/stu3/validation.html) 

### Using the FHIR Validator

[Using the FHIR Validator](https://wiki.hl7.org/Using_the_FHIR_Validator). 

To download the validator: [https://fhir.github.io/latest-ig-publisher/org.hl7.fhir.validator.jar](https://fhir.github.io/latest-ig-publisher/org.hl7.fhir.validator.jar)

To validate against UKCore resources you need to use this ImplementationGuide. 

If you wish to use a local copy of this IG's conformance resources they are available on [Simplifier](https://simplifier.net/UKCore). 

#### Usage

To use this IG with the **FHIR Validator**  you need to specify **https://project-wildfyre.github.io/uk-testcore-r4/** in the **-ig** parameter.

> java -jar org.hl7.fhir.validator.jar patient.xml -version 4.0.0 -ig https://project-wildfyre.github.io/uk-testcore-r4/

Alternatively 

> java -jar org.hl7.fhir.validator.jar patient.xml -version 4.0.0 -ig uk.testcore.r4



