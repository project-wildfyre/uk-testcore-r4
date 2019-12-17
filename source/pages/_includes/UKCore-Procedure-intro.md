
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile allows exchange of details of current and historical procedures performed on or for an individual. A procedure is an activity that is performed on, with, or for an individual as part of the provision of care. Examples include surgical procedures, diagnostic procedures, endoscopic procedures, biopsies, counselling, physiotherapy, personal support services, adult day care services, non-emergency transportation, home modification, exercise, etc. Procedures may be performed by a healthcare professional, a service provider, a friend or relative or in some cases by themselves.


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Procedure profile:

- Query for procedures performed on a Patient
- Record or update a procedure performed on a Patient

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The Formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Procedure resource must have:**

1. A status
2. A code that describes the type of procedure performed on the Patient
3. A Patient
4. When the procedure was performed

**Profile specific implementation guidance:**

Procedure codes SNOMED-CT are required. Further guidance will be issued on the use of SNOMED CT in a later release.

A procedure including an implantable device SHOULD use Procedure.focalDevice with a reference to the UK Core Device Profile.

### Example ###

- [UK Core Procedure Example](UKCore-Procedure-Example.html)
