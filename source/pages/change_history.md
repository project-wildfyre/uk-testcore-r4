---
title: Change History
layout: default
active: history
---

Changes that have been applied for this Implementation Guide version and FHIR assets contained within it will be documented here.

## 1.0.0 ##

This initial baseline release contains all of the CareConnect FHIR profiles published on [https://fhir.hl7.org.uk](https://fhir.hl7.org.uk), with the following corrections applied:

[Issue #3: ValueSet-CareConnect-ACVPU-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/3) - correction to SNOMED CT expression. Upversioned to 1.0.1.

[Issue #4: ValueSet-CareConnect-AllergyCode-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/4) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[issue #5: ValueSet-CareConnect-AllergyManifestation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/5) - correction to SNOMED CT expression. Upversioned to 1.2.1.

[Issue #6: ValueSet-CareConnect-AllergySeverity-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/6) - correction to SNOMED CT expression. Upversioned to 1.1.1. 

[Issue #7: ValueSet-CareConnect-CareSettingType-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/7) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[Issue #8: ValueSet-CareConnect-DocumentType-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/8) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[Issue #9: ValueSet-CareConnect-EmergencyCareDischargeStatus-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/9) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[Issue #10: ValueSet-CareConnect-ImmunizationExplanationReason-2](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/10) - correction to codes applied. Upversioned to 2.1.1.

[Issue #11: ValueSet-CareConnect-MedicationDosageRoute-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/11) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[Issue #12: ValueSet-CareConnect-ProcedureCode-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/12) - correction to SNOMED CT expression. Upversioned to 1.1.1.

[Issue #13: ValueSet-CareConnect-SpecimenBodySite-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/13) - correction to SNOMED CT expression. Upversioned to 2.1.1.

[Issue #14: ValueSet/CareConnect-BodySite-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/14) - removed whitespaces from value set bindings. Profiles upversioned to 1.1.1.

[Issue #15: ValueSet-CareConnect-ClinicalDisciplines-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/15) - correction to typo in name. Upversioned to 1.0.1.

[Issue #17: ValueSet-CareConnect-ReportCodeSnCT-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/17) - applied correction to SNOMED CT expression and removed typo in description. Upversioned to 1.1.1. 

[Issue #18: CodeSystem/CareConnect-MedicationFlag-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/18) - Correction to display value formatting. Upversioned to 1.0.1.

[Issue #19: CareConnect-ProblemList-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/19) - correct naming convention applied. Upversioned to 1.1.1.

[Issue #20: CareConnect-VitalSigns-Observation-1.code](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/20) - applied correct display value 'Vital signs finding (finding). Upversioned to 1.1.1.

[Issue #22: CareConnect-ACVPU-Observation-1.code](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/22) - removed incorrect terminology binding. Upversioned to 1.1.1.

[Issue #23: CareConnect-Condition-1.verificationStatus](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/23) - reinstated element 'verificationStatus' to meet Constraint (con-3): Condition.clinicalStatus SHALL be present if verificationStatus is not entered-in-error. Upversioned to 1.1.1.

[Issue #24: CodeSystem/CareConnect-ConditionRelationship-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/24) - Removed whitespaces from 'child' and 'sibling' codes. Upversioned to 1.1.1.

[Issue #25: CareConnect-ProblemHeader-Condition-1.category.coding.display](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/25  ) - Removed whitespace from fixed display value. Upversioned to 1.1.1.

[Issue #26: ValueSet-CareConnect-MaritalStatus-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/26) Correction to display value for code 'U - unmarried'

[Issue #27: Extension-CareConnect-FastingStatus-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/27) - Removed whitespace. Upversioned to 1.0.2.

[Issue #28: Extension-CareConnect-ResidentialStatus-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/28) - Removed whitespace. Upversioned to 1.1.1.

[Issue #29: CareConnect-BloodPressure-Observation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/29) - Removed whitespace as part of existing upversion.
 
[Issue #30: CareConnect-BodyTemperature-Observation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/30) - Removed whitespace as part of existing upversion.

[Issue #31: CareConnect-DocumentReference-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/31) - Removed whitespace. Upversioned to 1.1.1.

[Issue #32: CareConnect-HeartRate-Observation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/32) - Removed whitespace as part of existing upversion.

[Issue #33: CareConnect-Observation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/33) - Removed whitespace. Upversioned to 1.2.1.

[Issue #34: CareConnect-RespiratoryRate-Observation-1](https://github.com/nhsconnect/FHIR-STU3-Core-IG-1.0.0/issues/34) - Removed whitespace as part of existing upversion.