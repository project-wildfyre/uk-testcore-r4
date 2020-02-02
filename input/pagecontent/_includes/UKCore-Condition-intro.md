
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows recording of detailed information about a condition, problem, diagnosis, or other event, situation, issue, or clinical concept that has risen to a level of concern. The condition could be a point in time diagnosis in the context of an encounter, it could be an item on the practitioner’s problem list, or it could be a concern that doesn’t exist on the practitioner’s problem list. Often, a condition is about a clinician's assessment and assertion of a particular aspect of an individual's state of health. It can be used to record information about a disease/illness identified from application of clinical reasoning over the pathologic and pathophysiologic findings (diagnosis), or identification of health issues/situations that a practitioner considers harmful, potentially harmful and may be investigated and managed (problem), or other health issue/situation that may require ongoing monitoring and/or management (health issue/concern).


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Condition profile:

- Query for a Patient’s current or historical conditions
- Record or update a Patient’s conditions

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Condition resource must have:**

1. A status of the condition*
2. A category
3. A code that describes the condition
4. A patient

**Each Condition resource must support:**

1. A verification status

**Profile specific implementation guidance:**

There is no specific guidance for this profile.

### Example ###

- [UK Core Condition Example](UKCore-Condition-Example.html)

