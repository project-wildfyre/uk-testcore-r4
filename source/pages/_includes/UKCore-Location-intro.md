
{% assign id = {{page.id}} %}

{{site.data.structuredefinitions.[id].description}}

<!-- end TOC -->
### Profile Purpose ###

This profile can be used to exchange details and position information for a physical place where services are provided and resources and participants may be stored, found, contained, or accommodated.

A location includes both incidental locations (a place which is used for healthcare without prior designation or authorisation) and dedicated, formally appointed locations. Locations may be private, public, mobile or fixed and scale from small freezers to full hospital buildings or parking garages.

### Example Usage Scenarios ###

The following are example usage scenarios for the UK Core location profile:

- Query for a location using the query parameter type(Location.type) for a known location type. 
- Exchange location demographic information within a FHIR document or message.

### Profile Minimum Viable Content (Mandatory and Must Support Data Elements) ###

The following data-elements are mandatory (i.e data MUST be present) or must be supported if the data is present in the sending system (Must Support definition). They are presented below in a simple human-readable explanation. Profile specific guidance and examples are provided as well. The Formal Profile Definition below provides the formal summary, definitions, and terminology requirements.

**Each Location must have:**

1. A name

**Each Location must support:**

1. A type
2. An identifier
3. A telecom
4. An address


### Example ###

- [UK Core Location Example](UKCore-Location-Example.html)