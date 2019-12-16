
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->

### Profile Purpose ###

This profile allows exchange of demographics and other administrative information about an individual receiving care or other health-related services.

### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Patient profile:

- Query for a Patient demographic information using the query parameter identifier (Patient.identifier) for a known NHS number
- Query for a Patient demographic information using query parameters such as family, given, birthdate, and gender.
- Exchange Patient demographic information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The Formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Patient resource must have:**

1. A patient identifier (e.g. NHS Number, CHI Number, Local patient identifier)

**Each Patient resource must support:**

1. A patient name
2. A gender
3. contact detail (e.g. a telephone number or an email address)
4. A birth date
5. An address
6. A communication language
7. An ethnicity

**Profile specific implementation guidance:**

When the Patient profile is being used with an NHS Number in the patient.identifier elements the status of the NHS Number MUST also be carried.

### Example ###

- [UK Core Patient Example](UKCore-Patient-Example.html)