
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of information about a person that is involved in the care for an individual, but who is not the target of healthcare, nor has a formal responsibility in the care process.
### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core RelatedPerson profile:

- Query for related person information for a given Patient
- Exchange related person information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each RelatedPerson resource must have:**

1. A patient

**Each RelatedPerson resource must support:**

1. A relationship
2. A name
3. A telecom

**Profile specific implementation guidance:**



### Example ###

- [UK Core RelatedPerson Example](UKCore-RelatedPerson-Example.html)

