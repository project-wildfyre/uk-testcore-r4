
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of a formally or informally recognised grouping of people or organisations formed for the purpose of achieving some form of collective action. Includes companies, institutions, corporations, departments, community groups, healthcare practice groups, etc.

### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Organisation profile:

- Query for organisation information using the query parameter identifier (Organization.identifier) for a known ODS code. 
- Exchange organisation information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Organisation resource must have:**

1. An identifier (e.g. ODS Code, local identifier)
2. A name

**Each Organisation resource must support:**

1. A telecom
2. An address

**Profile specific implementation guidance:**

There is no specific guidance for this profile.

### Example ###

- [UK Core Organisation Example](UKCore-Organisation-Example.html)