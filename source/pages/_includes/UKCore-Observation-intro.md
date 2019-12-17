
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of information of Measurements and simple assertions made about an individual, device or other subject. More specific observation profiles will be added to this specification in later releases when the use cases for more specific observations have been evaluated.

Note: this profile MUST not be used where a more specific UK Core profile exists.


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Observation profile:

- Query for observations associated with a Patient
- Record or update an observation about a Patient

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Observation resource must have:**

1. A status
2. A code that describes the type of observation about the Patient
3. A Patient
4. When the observation was made

**Profile specific implementation guidance:**



### Example ###

- [UK Core Observation Example](UKCore-Observation-Example.html)