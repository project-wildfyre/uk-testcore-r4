
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of information about an interaction between an individual and healthcare provider(s) for the purpose of providing healthcare service(s) or assessing the health status of an individual.


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Encounter profile:

- Query for a specific patient encounter
- Query for recent patient encounters
- Record or update an encounter

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Encounter resource must have:**

1. A status
2. An classification such as inpatient, outpatient or emergency
3. An encounter type
4. A patient
5. 
**Each Encounter resource must support:**

1. An encounter identifier
2. Providers involved in the encounter
3. When the encounter occurred
4. Reason for the visit
5. The discharge disposition
6. Where the encounter occurred

**Profile specific implementation guidance:**


### Example ###

- [UK Core Encounter Example](UKCore-Encounter-Example.html)