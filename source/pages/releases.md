---
title: UK Core releases
layout: default
active: releases
---


<!-- TOC  the css styling for this is \pages\assets\css\project.css under 'markdown-toc'-->

* Do not remove this line (it will not be displayed)
{:toc}


<!-- end TOC -->

### Overview
This section details the release approach that will be applied for the UK Core specification ('UK Core').

**Important:**

The release approach documented here is for consultation with early adopters of the UK Core and subject matter experts, and therefore subject to change. 

## Release of the UK Core specification
Each version of the UK Core will be in the following format:

- UK Core 'Month of release' 'Year of release', for example 'UK Core February 2020’.

The ‘business version’ which is the default behaviour for versioning by the HL7 IG tooling will not be used. The FHIR assets will be versioned independently of the UK Core.


## Release Schedule
The first few iterations of the UK Core will be released on an ad hoc basis. This is in order to facilitate a speedier review, feedback and re-development cycle based on first of type implementations experience and consultation with subject matter experts.  

Once it has become more stable, the UK Core is proposed to have a tightly defined release schedule. This is currently proposed to be every three months for a major release (see release types). The release intervals must be small enough to correct issues and bugs but large enough to enable development to commence on a stable specification. This will be a challenge in the early UK Core, once several programmes start implementations in parallel. 

## Support for Releases
Only two releases of the UK Core will be supported concurrently, and it is deemed necessary that implementers plan to move their developments to the latest UK Core as soon as possible. The concept may require implementers to adopt new practices to facilitate this approach, however it is seen as paramount to the UK Core concept and interoperability. 

## The Release Cycle

The diagram below illustrates the release cycle of the UK Core.

{% include img.html img="release_diagram.svg" %}

Note: Due to the time required to implement a UK Core based solution, it is anticipated there will be a requirement to have two live releases at any one time. 

## Release Types
The release of a UK Core specification needs to indicate the magnitude of the changes supported by a new release, so that implementers can gauge the impact of moving to a new release on their current development. It is envisioned that the release will have been developed in full agreement and collaboration with all current implementers, including the scale and scope of the release. 

**Major** - incompatible changes defined as a breaking change. The description of why this is defined as a breaking change will be contained in the release. 
Examples: 

-  Removal of a profile
-  Major changes to a profile - which will break previous implementations
-  Major changes to the pattern or approach for representing a clinical concept

**Minor** - functionality added in a backwards-compatible manner corrections or other non-Breaking changes.
Examples:

- Addition of new profile
- Minor changes to a profile - which are non breaking
- Addition of a new extension to the Extension Library

**Patch** - backwards-compatible bug fixes, for example typos.
  
