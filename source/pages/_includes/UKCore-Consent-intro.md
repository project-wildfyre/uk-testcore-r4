
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

The purpose of this profile is to be used to express a consent regarding healthcare. There are four anticipated uses for the Consent resource, all of which are written or verbal agreements by a healthcare consumer [grantor] or a personal representative, made to an authorised entity [grantee] concerning authorised or restricted actions with any limitations on purpose of use, and handling instructions to which the authorised entity must comply:
 
- Privacy Consent Directive: Agreement to collect, access, use or disclose (share) information.
- Medical Treatment Consent Directive: Consent to undergo a specific treatment (or record of refusal to consent).
- Research Consent Directive: Consent to participate in research protocol and information sharing required.
- Advance Care Directives: Consent to instructions for potentially needed medical treatment (e.g. DNR).


### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core Consent profile:

- Query for consent information for a Patient
- Record or update consent information for a Patient

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The Formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Consent resource must have:**

1. A status
2. Scope
3. A Category 

**Each Consent resource must support:**

1. A patient
2. A Performer - Who is agreeing to the policy and rules
3. An Organization - Custodian of the consent 

**Profile specific implementation guidance:**


### Example ###

- [UK Core Consent Example](UKCore-Consent-Example.html)

