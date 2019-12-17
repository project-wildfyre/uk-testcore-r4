
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of a flat, possibly ordered collection of records. The list profile can be used in many places, including allergies, medications, alerts, family history, medical history, etc. This list profile can be used to support individual-specific clinical lists as well as lists that manage workflows such as tracking patients, managing teaching cases, etc. The list profile supports lists that are homogeneous – consisting of only one type of resource (e.g. allergy lists) as well as heterogeneous – containing a variety of resources (e.g. a problem list including Conditions, AllergyIntolerances, recent Procedures, etc.).

### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Procedure profile:

- A list of allergies for a Patient
- A list of current medication for a Patient
- Record or update a list of information for Patient

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each List resource must have:**

1. A status
2. A mode
3. A code that describes the purpose of the list

**Each List resource must support:**

1. A list item
2. A date when the list was prepared
3. A list subject
4. An emptyReason


**Profile specific implementation guidance:**


### Example ###

- [UK Core List Example](UKCore-List-Example.html)