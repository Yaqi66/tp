# Developer Guide

## Acknowledgements

Beyond the Java Standard Library, no other libraries were used. No code was reused as well.

## Setting up, getting started

{Update with instructions on setting up}

## Design

### Architecture

### UI Component

{Update with information about UI Architecture}

### Command Component

{Update with information about Command Architecture}

### Parser Component

{Update with information about Parser Architecture}

### Storage Component

{Update with information about Storage Component}

## Implementation

This section describes some noteworthy details on how certain features are implemented.

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Add Medication Feature

This add-medication mechanism allows users to record a new medication 
with the name, dosage, quantity and expiry date information.
```
add /n NAME /d DOSAGE /q QUANTITY /e EXPIRY [/t TAG]
```
#### How it works

The following steps describe how an add command is processed. 

1. The user enters `add /n Paracetamol /d 500mg /q 100 /e 2026-12-31 /t Painkiller`.
2. `PharmaTracker.run()` reads the user input and passes the raw string to `Parser.parse()`.
3. `Parser.parse()` identifies the command word `add`.
4. The parser then delegates the specific extract methods 
   (such as `extractName()`, `extractDosage()`, `extractQuantity` and `extractExpiryDate()`). 
   These methods locate the corresponding flag prefixes (e.g. `/n`, `/d`, `/q`, `/e`) using string indexing to extract the arguments.
   Optional flags are extracted using `extractFlag()` or `extractWarnings()`.
5. The extracted values are used to create a new `AddCommand` object. 
6. `PharmaTracker.run()` calls `AddCommand.execute()`, which creates a new `Medication` object and adds it to the `Inventory`.
7. Finally, `Ui.printAddedMessage()` is called to display a confirmation message to the user. 

## Product scope
### Target user profile

Pharmacy staff (pharmacists, pharmacy technicians) who:
- Need to manage a medication inventory in a small to mid-size pharmacy
- Prefer a fast CLI-based workflow over GUI applications
- Are comfortable typing commands and can type quickly
- Need to track medication stock, expiry dates, and dispensing

### Value proposition

{Describe the value proposition: what problem does it solve?}
Fast, lightweight medication tracking without needing a database or internet connection

## User Stories

| Version | As a ...            | I want to ...                          | So that I can ...                                  |
|---------|---------------------|----------------------------------------|----------------------------------------------------|
| v1.0    | new user            | see usage instructions                 | refer to them when I forget how to use the app     |
| v1.0    | pharmacist          | add medications to inventory           | track stock levels                                 |
| v1.0    | pharmacist          | list all medications                   | see what is currently in stock                     |
| v1.0    | pharmacist          | delete a medication                    | remove discontinued or incorrect entries           |
| v1.0    | pharmacist          | find medication by keyword             | quickly locate a specific drug without scrolling   |
| v1.0    | pharmacist          | sort medications by expiry date        | identify medications expiring soon                 |
| v1.0    | pharmacist          | view detailed medication information   | check dosage form, directions, and warnings        |
| v2.0    | pharmacist          | print a medication label               | attach it to dispensed packages                    |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launching the application

1. Open a terminal in the project root directory.
2. Run `./gradlew run` (Linux/macOS) or `.\gradlew run` (Windows).
3. The welcome banner and `Enter command:` prompt should appear.

### Adding a medication

1. Enter: `add /n Paracetamol /d 500mg /q 100 /e 2026-12-31 /t painkiller`
2. **Expected:** Confirmation message showing the medication was added.
3. With optional fields: `add /n Amoxicillin /d 250mg /q 50 /e 2026-06-01 /t antibiotic /df Capsule /mfr Pfizer /warn "May cause allergic reactions"`

### Listing medications

1. Enter: `list`
2. **Expected:** All medications displayed with index, name, dosage, quantity, expiry, and tag. Items with quantity ≤ 10 show `[LOW STOCK]`.

### Finding a medication

1. Enter: `find Paracetamol`
2. **Expected:** All medications whose name contains "Paracetamol" (case-insensitive) are listed.