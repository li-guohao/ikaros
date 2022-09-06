name: Ikaros feature request
description: Suggest an idea for this project
title: "[Feature] "
labels: [ "feature" ]
body:
- type: markdown
  attributes:
  value: |

      Thank you for finding the time to propose new feature!

      We really appreciate the community efforts to improve Ikaros.

- type: checkboxes
  attributes:
  label: Search before asking
  description: >
  Please make sure to search in the [issues](https://github.com/li-guohao/ikaros/issues?q=is%3Aissue) first to see
  whether the same feature was requested already.
  options:
  - label: >
  I had searched in the [issues](https://github.com/li-guohao/ikaros/issues?q=is%3Aissue) and found no similar
  feature requirement.
  required: true

- type: textarea
  attributes:
  label: Description
  description: A short description of your feature

- type: textarea
  attributes:
  label: Use case
  description: What do you want to happen?
  placeholder: >
  Rather than telling us how you might implement this feature, try to take a
  step back and describe what you are trying to achieve.

- type: textarea
  attributes:
  label: Related issues
  description: Is there currently another issue associated with this?

- type: checkboxes
  attributes:
  label: Are you willing to submit a PR?
  description: >
  This is absolutely not required, but we are happy to guide you in the contribution process
  especially if you already have a good understanding of how to implement the feature.
  Ikaros is a totally community-driven project and we love to bring new contributors in.
  options:
  - label: Yes I am willing to submit a PR!

- type: markdown
  attributes:
  value: "Thanks for completing our form!"