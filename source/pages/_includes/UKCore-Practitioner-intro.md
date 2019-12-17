
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->

### Profile Purpose ###

This profile allows exchange of information about all individuals who are engaged in the healthcare process and healthcare-related services as part of their formal responsibilities and this profile is used for attribution of activities and responsibilities to these individuals. 

### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Practitioner profile:

- Query for practitioner information using the query parameter identifier(practitioner.identifier) for a known SDS identifier. 
- Exchange practitioner information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Practitioner must have:**

1. An identifier (e.g. SDS ID, local identifier)
2. A name

**Each Practitioner must support:**

1. A telecom
2. An address

### Example ###

- [UK Core Practitioner Example](UKCore-Practitioner-Example.html)