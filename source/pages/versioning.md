---
title: Versioning of UK Core FHIR Assets 
layout: default
active: Versioning
---

<!-- TOC  the css styling for this is \pages\assets\css\project.css under 'markdown-toc'-->

* Do not remove this line (it will not be displayed)
{:toc}


<!-- end TOC -->


### Overview
This section details the versioning approach that will be applied for the FHIR assets contained within the UK Core.

These Assets are:
- StructureDefinitions (Profiles and Extensions)
- ValueSets
- CodeSystems
- ConceptMaps
   
**Important**

The versioning approach documented here is currently under consultation and is therefore subject to change.

## Versioning of the UK Core Assets
The ‘fixed-business-version’ property, which is the default HL7 IG Publisher behaviour for versioning of StructureDefinitions, will not be used. The FHIR assets will be versioned independently of the implementation guide.

## URL and Name Format for Profiles
The URL format is defined as:

'URL' / 'FHIR Version' / 'FHIR asset type' / 'Resource ID'

For example - https://fhir.nhs.uk/R4/StructureDefinition/UKCore-Patient.

Note : The actual URL will depend on where the FHIR assets are hosted.

## URL and Profile Versioning
StructureDefinitions will not carry version information in the URL, but will use the internal version element as per the FHIR Standard. This is demonstrated below:

{% include img.html img="sd_versioning.svg" %} 

## Instance Conformance Identification

For some information flows, there is a requirement to identify which UK Core profile(s) an ‘on the wire’ instance conforms to for the purpose of validation and/or conformance testing. This profile conformance is declared using the profile.meta element. This element carries the profile URL appended with the version information. 

The format is:

'URL' "\|" 'version'

For example - https://fhir.nhs.uk/R4/StructureDefinition/UKCore-Patient\|1.2.0.

This is demonstrated below:

{% include img.html img="instance_versioning.svg" %}

