# Karthik Kathiresh - Project Portfolio Page

## Overview
PharmaTracker is a command-line application for pharmacy staff to manage medication inventory
and customer records. It supports adding, finding, dispensing, and restocking medications,
as well as managing customer information and tracking dispensing history. It is optimized for
fast typists who prefer a CLI workflow over GUI applications.

## Summary of Contributions

### Code Contributed: [View my code on RepoSense](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=karthikkathiresh&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

### Enhancements Implemented
* **Application Architecture and Base Code Setup** (v1.0, [PR #21](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/21)): Set up the foundational codebase and main execution loop for the `PharmaTracker` application. This involved implementing the main `PharmaTracker` entry class, the `Medication` and `Inventory` classes to manage data, the `Ui` class for input-output handling, and a skeleton for the `Parser` class. This is extremely important so that all team members could begin working on their features independently.
* **Add Medication Feature** (v1.0, [PR #54](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/54)): Allows users to log a new medication into the inventory. Captures mandatory details and optional attributes. This feature is the foundation of populating the inventory with medications, and for enabling other features. 
    * **Technical Highlights**: Implemented `AddCommand` which executes this feature. Updated `Parser` to handle variable-length inputs, and `Ui` to print a confirmation message to the user. 
* **Delete Medication Feature** (v1.0, [PR #47](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/47)): Allows users to remove a medication from the inventory by referencing its 1-based index in the inventory list. This feature allows users to remove items they deem not required anymore. 
    * **Technical Highlights**: Implemented `DeleteCommand` which handles input parsing and translating the 1-based user index to a 0-based system index. 
* **Update Medication Feature** (v2.0, [PR #105](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/105)): Allows users to selectively modify specific fields of an existing medication record. This is essential for maintaining accurate data without having to delete and re-add items. 
    * **Technical Highlights**: Implemented `UpdateCommand` to accept an index alongside any combination of optional update flags. Enhanced `Parser` to flexibly extract these parameters. 
* **Add Customer Feature** (v2.0, [PR #89](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/89)): Registers a new customer profile into the system. This expands the application's scope to a holistic pharmacy management tool, laying the foundation for customer-linked dispensing features.
  * **Technical Highlights**: Implemented `AddCustomerCommand` and updated `Parser` with dedicated extraction methods to parse user inputs safely.
* **Delete Customer Feature** (v2.0, [PR #107](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/107)): Enables users to permanently remove a customer from the database by referencing their 1-based index in the customer list.
  * **Technical Highlights**: Implemented `DeleteCustomerCommand` which has been interfaced with `CustomerList` and `Ui` to ensure safe deletion and clear user feedback.
* **Flexible Date Parsing and Validation** (v2.0.1, [PR #138](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/138)): Implemented a robust date parsing mechanism that accepts multiple date formats from the user. This also enforces strict business rules for medication expiry dates to prevent illogical date entries. 
  * **Technical Highlights**: Implemented parsing logic within `MedicationParserUtil` using `DateTimeFormatter` to support and normalize formats such as `yyyy-MM-dd`, `d/M/yyyy`, and `d-M-yyyy`. Added active validation rules to reject dates that are already in the past or exceed a realistic 10-year threshold.
* **Assertion and Logging** (v1.0 - v2.1): Added assertions and `java.util.logging` to `AddCommand`, `DeleteCommand`, `UpdateCommand`, `AddCustomerCommand` and `DeleteCustomerCommand`.

### Contributions to Testing
* Wrote unit tests for `AddCommand`, `DeleteCommand`, `UpdateCommand`, `AddCustomerCommand` and `DeleteCustomerCommand` using **JUnit**.

### Contributions to the User Guide (UG)
* **Medication Management**: Wrote the usage instructions, parameter details and expected outputs for the `add`, `delete` and `update` medication commands. 
* **Customer Management**: Wrote the usage instructions, parameter details and expected outputs for the `add` and `delete` commands.

### Contributions to the Developer Guide (DG)

* **Architecture:** Authored the System Architecture section detailing the high-level design and interactions between core components (UI, Logic, Model, and Storage).
* **Implementation Details:** 
  * Documented the design and execution flow for the medication `add`, `delete`, and `update` commands.
  * Documented the design and execution flow for the customer `add` and `delete` commands.
* **UML Diagrams:** 
  * Created sequence diagrams for the medication and customer management commands to illustrate the execution flow. These were done for `AddCommand`, `DeleteCommand`, `UpdateCommand`, `AddCustomerCommand` and `DeleteCustomerCommand`.
  * Designed class diagrams showing the structure and relationships of the Medication and Customer components within the Model.

### Project Management & Team-Based Tasks
* Set up the team GitHub organization and repository (along with Alson)
* Reviewed PRs from all teammates from `v1.0` to `v2.1`.
* Managed releases `[v1.0]` - `[v2.0]` on GitHub. This includes publishing the `PharmaTracker.jar` file, `UserGuide` and `DeveloperGuide`.
* Maintained the issue tracker by creating and assigning tasks for the `[v1.0]` milestone.

### Community & Tooling
* **PRs Reviewed:** Reviewed PRs from teammates with non-trivial feedback (Examples: [#29](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/29), [#37](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/37), [#91](https://github.com/AY2526S2-CS2113-T10-3/tp/pull/91)).
* **Bug Reporting:** Reported bugs and provided suggestions for other teams during the practical exam.