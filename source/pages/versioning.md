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
This section details the versioning approach that will be applied for the FHIR Assets contained within the UK Core.

These Assets are:
- Profiles (StructureDefinitions)
- ValueSets
- CodeSystems
- ConceptMaps
- Extensions
   
**Important:**

The versioning approach documented here is currently out for consultation and therefore subject to change.

## Versioning of the UK Core Assets
The ‘business version’ which is the default behaviour for versioning by the HL7 IG tooling will not be used. The FHIR assets will be versioned independently of the UK Core.

## URL and Name Format for Profiles
The URL format is defined as

'URL' / 'FHIR Version' / 'FHIR asset type' / 'Resource ID'

For example - https://fhir.nhs.uk/R4/StructureDefinition/UKCore-Patient.

Note : The actual URL will be dependant of where the FHIR assets are hosted.
  

## URL and Profile Versioning
The FHIR profiles (StructureDefinitions) will have not carry version information in the URL, however the UK Core FHIR profiles will use the internal version element as per the FHIR Standard. See Fig 1 below:

{% include img.html img="sd_versioning.svg" %} 

## Instance Conformance Identification

For some information flows there a requirement to identify which UK Core profile or profiles an ‘on the wire’ instance conforms to for the purpose of validation and/or conformance testing. This identification is done using the profile meta element. This element carries the profile URL appended with the version information. 

The format is:

'URL' "\|" 'version'

For example - https://fhir.nhs.uk/R4/StructureDefinition/UKCore-Patient\|1.2.0 see fig2 below for a real example.

{% include img.html img="instance_versioning.svg" %} 