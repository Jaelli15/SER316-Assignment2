# Assignment 3 Code Review and Fix Record

**Student name:** Jason Elliott  
**ASU ID:** Jaelli15 
**Review branch:** `Review`  
**Blackbox commit used as the branch point:** [commit hash]

## AI-use statement

Choose one and complete it:

- [X] I did not use generative AI for this report.
- [ ] I used generative AI only for permitted grammar/table-formatting help. Tool and exact use: [describe].

The findings, evidence, severity judgments, fixes, and traceability decisions below are my own work.

## Categories and severity

- **CR - Correctness/functional:** behavior can be wrong, unsafe, or inconsistent with the specification.
- **MA - Maintainability/design:** duplication, excessive coupling, poor structure, or another obstacle to safe change.
- **CS - Coding standard/readability:** naming, formatting, documentation, or clarity problem.
- **Critical:** likely data loss, serious security problem, or system-wide failure.
- **High:** major behavior is wrong or a common operation fails.
- **Medium:** limited behavioral problem or meaningful maintenance risk.
- **Low:** localized readability, consistency, or minor maintenance problem.

## Review findings

Record exactly four distinct findings. Include at least one CR, one MA, and one CS finding. The fourth may use any category.

| ID | File and current line(s) | Category | Concrete evidence from the code | Impact and severity rationale | Focused correction |
|---|---|---|---|---|---|
| D1 | `Checkout.java:246` | CR | return typeString == expectedType.toString() | High: The use of == compares references no the contents so this only works on the off chance that both strings are the same instance | Change it to typeString.equals(expectedType.toString()) |
| D2 | `Book.java:70` | MA | isAvailable() and checkAvailability() check two different sources | Medium: The two can separate and return different results depending on when asked | Get rid of available fields and just have checkAvailability return isAvailable |
| D3 | `Patron.java:117` | CS | if-else chain is cramped and hard to read | Low: Hard to read and using a series of one line statements could lead to errors when edited | Convert to a switch case for easier readability |
| D4 | `Book.java:112` | CR | resetAvailability also works with reference books | Medium: Incorrectly makes reverence books available which is against the constructors rules  | Change resetAvailability to check if reference book first |

## Selected fixes and traceability

Select two findings, including at least one CR finding. Each change should have its own GitHub issue and focused commit.

| Defect ID | GitHub issue link | Fix commit hash/link | What changed |
|---|---|---|---|
| D1 | [GitHub](https://github.com/Jaelli15/SER316-Assignment2/issues/1) | [Commit](https://github.com/Jaelli15/SER316-Assignment2/commit/feccb0a4e1b4c3fc909a6f374c8b0deb8c302bc4) | Changed "return typeString == expectedType.toString()" to "return typeString.equals(expectedType.toString())" |
| D4 | [GitHub](https://github.com/Jaelli15/SER316-Assignment2/issues/2) | [Commit](https://github.com/Jaelli15/SER316-Assignment2/commit/7a5bf823a61c0cfc1fc8b23e923470d62c030e8a) | Updated to check reference type: this.availableCopies = referenceOnly ? 0 : totalCopies |

## Fix rationale

For each fix, explain in one or two specific sentences why the change makes the codebase better.

**Fix 1:** Currently the equality check for strings will occasionally work if the two strings are the same instance but there is a chance that even if both strings are equal then they could return false. Changing to typeString.equals(expectedType.toString()) means that it will correctly check the two string contents equality. 

**Fix 2:** Currently the resetAvailability function would reset availability of all book types. This was going against the main ruleset that declared that reference books availability should not change. Updating it to check for reference type and return no change or correct change means that it will not affect the availability state of the reference type books.

