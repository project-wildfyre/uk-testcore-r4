---
title: UK Core releases
layout: default
active: releases
---

### Overview
This section details the release approach that will be applied for the UK Core Specification.

**Important:**

The release approach documented here is currently out for consultation and therefore subject to change. The purpose of this is to share the proposed approach with early adopters of the UK Core and subject matter experts.

## Release of the UK Core specification
The version of the UK Core will be in the following format:

- UK Core 'Month of release' 'Year of release' for example 'UK Core February 2020’.

## Release Schedule
The first few iterations of the UK Core will be released on a fairly ad hoc manner to facilitate a speedier review, feedback and re-development cycle based on first of type implementations experience and consultation with subject matter experts.  
Once it has become more stable, the UK Core is proposed to have a tightly defined release schedule. This is currently proposed to be every three months for a major release (See release types). The release intervals must be small enough to correct issues and bugs but large enough to enable development to commence on a stable specification. This will be a challenge in the early UK Core, once several programmes start implementations in parallel. 

## Support for Releases
Only two releases of the UK Core will be supported at any one time and it is deemed necessary that implementers plan to move their developments to the latest UK Core asap. The concept may require implementers to adopt new practices to facilitate this approach , however it is seen as paramount to the UK Core concept and interoperability. 

## The Release Cycle

The diagram below illustrates the release cycle of the UK Core.

{% include assets/images/release_diagram.svg %}   

## Release Types
The release of a UK Core specification needs to indicate the magnitude of the changes supported by a new release so that implementers can gauge the impact of moving to a new release on their current development. It is envisioned that the release will have been constructed in full agreement and in collaboration with all current implementers and therefore the scale/ scope of the release is agreed as necessary. 

**Major** - incompatible changes defined as a breaking Change. The description of why this is defined as a breaking change will be contained in the release. 
Examples: 

-  Removal of a profile
-  Major changes to a profile - which will break previous implementations
-  Major changes to the pattern or approach for representing a clinical concept

**Minor** - functionality added in a backwards-compatible manner corrections or other non-Breaking changes.
Examples:

- Addition of new profile
- Minor changes to a profile - which are non breaking
- Addition of a new extension to the library

**Patch** - backwards-compatible bug fixes. Typo’s etc.
  
