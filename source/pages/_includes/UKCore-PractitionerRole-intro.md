
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of a specific set of roles, specialties and services that a practitioner may perform at an organisation for a period of time.


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core PractitionerRole profile:

- Query for a Practitioner role using the query parameter identifier (PractitionerRole.identifier) for a known SDS Role Id
- Query for a Practitioner using query parameters such as specialty (PractitionerRole.specialty) for a known specialty.
- Exchange practitioner role information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each PractitionerRole Resource must have:**

At least one of the must support elements must be present in any instance of this resource.

**Each PractitionerRole resource must support:**

1. An associated practitioner
2. An associated organization
3. A role
4. A Specialty
5. An associated location
6. Contact information

**Profile specific implementation guidance:**

There is no specific guidance for this profile.

### Example ###

- [UK Core PractitionerRole Example](UKCore-PractitionerRole-Example.html)