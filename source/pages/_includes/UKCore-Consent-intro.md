
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

The purpose of this profile is to be used to express a consent regarding healthcare. There are four anticipated uses for the Consent resource, all of which are written or verbal agreements by a healthcare consumer [grantor] or a personal representative, made to an authorised entity [grantee] concerning authorised or restricted actions with any limitations on purpose of use, and handling instructions to which the authorised entity must comply:
 
- Privacy Consent Directive: Agreement to collect, access, use or disclose (share) information.
- Medical Treatment Consent Directive: Consent to undergo a specific treatment (or record of refusal to consent).
- Research Consent Directive: Consent to participate in research protocol and information sharing required.
- Advance Care Directives: Consent to instructions for potentially needed medical treatment (e.g. DNR).


### Profile Minimum Viable Content ###

The following elements are mandatory in this UKCore-Consent FHIR profile (i.e. MUST be present):

1.	status
2.	scope
3.	category

The Minimum Viable Content for this profile has not yet been defined and may be added in a later release.

### Example ###

- [UK Core Consent Example](UKCore-Consent-Example.html)

