# Assignment 4 — CI and Static Analysis Report

**Student name:**  Jason Elliott
**ASU ID:**  jaelli15
**Repository:**  
**Final Dev workflow run:**  
**Verified-build run on main:**  

This Markdown file is the complete report. Be specific where requested, but keep your answers concise. Do not create a separate PDF.

## Part 1: CI questions

Keep the complete section to approximately 250–400 words.

1. In the given workflow, what is the relationship among the workflow, its three jobs, each job's runner, and the steps inside a job?

   **Answer:** The workflow is the three jobs, Build, Test, and Static Analysis with each job having its own runner. Each job then contains steps that execute on that runner.

2. Why must each job repeat repository checkout and Java setup? 

   **Answer:** Each job has its own runner so each time it must check the repository and setup the java and gradle permissions. This sets up its own environment for each job and does not carry over to other jobs.

3. Why does the workflow use `./gradlew` rather than an unspecified system Gradle installation?

   **Answer:** It uses the gradle wrapper so that it correctly uses the specified gradle version making it more consistent for use on different machines or systems. 


4. Why is `contents: read` sufficient for this workflow?

   **Answer:** This workflow only needs to  read the repositories files to build, test, or analyze but does not need to modify any code. 


## Part 2: Static-analysis findings

### 2.1 Initial and final results

Record only the total number of findings before and after your changes in Checkout.java. Do not list every finding here.

| Tool | Initial finding count | Final finding count |
|---|---:|---:|
| Checkstyle | 39 | 36 |
| SpotBugs | 6 | 2 |


### 2.2 Finding decisions

Use one row per finding you investigated in `Checkout.java`. For SpotBugs, include every finding in that file. For Checkstyle, include at least three meaningful findings when available.

| Tool and rule/category | Line number(s) | Why it matters | Decision and change |
|---|---:|---|---|
| Checkstyle | 13 | Currently MAX_FINE_AMOUNT is considered a magic number | Fixed by making the variable final so that it cannot be changed later in the program |
| Checkstyle | 38 | Currently there is no javadoc comment to explain the constructor | Fixed by adding a javadoc comment to define the constructor as being the initial setup |
| Checkstyle | 162 | No whitespace preceeding the '{' | Fixed by adding a whitespace before the brace, it helps with readability and uniform through program |
| SpotBugs | 294 | Currently two strings are compared using '==' | Fixed by changing it to typeString.equals(expectedType.toString()) to properly compare the content of each string |
| SpotBugs | 387 | Currently getInventory() returns the actual bookList which could be dangerous for potential edits from outside the program | Fixed by returning a copy of the bookList and not the original |
| SpotBugs | 391 | Currently getPatrons() returns the actual patrons list which could be dangerous for potential edits from outside the program | Fixed by returning a copy of the patrons list and not the original |
| SpotBugs | 13 | MAX_FINE_AMOUNT is not final but should be | Fixed by making it final so that it can not be changed by accident or maliciously |
| SpotBugs | 25 | checkoutDate is unused | Left the variable in the program due to initial javadoc saying that checkoutDate is stored internally |
| SpotBugs | 26 | dueDate is unused | Left the variable in the program due to it being added in transaction and is used when a book is checkout to store the due date |

## Part 3: CI workflow evidence

### 3.1 Completed workflow

1. Were you able to complete the Test and Static Analysis jobs?

   **Answer:** Yes, and all reports show on GitHub workflow.

2. Did the Static Analysis job pass? Explain what happened in its Checkstyle and SpotBugs steps and why.

   **Answer:** The Static Analysis job passed and both the Checkstyle and SpotBugs steps returned a Build Successful prompt. The Checkstyle test shows the left over 36 warnings and the SpotBugs test shows the 4 warnings still showing from Patron and Checkout that did not need changed.

### 3.2 Workflow-run diagnosis
Provide information from your final run. Build and Test must pass. Static Analysis must run and upload its reports, but it may still report a failed job.

**Run URL:**  [Run](https://github.com/Jaelli15/SER316-Assignment2/actions/runs/43032445342)
**Commit:**  [Commit](https://github.com/Jaelli15/SER316-Assignment2/commit/eae3fa7f24a5499f8b359cb2e2926005170ad85d)
**Jobs that ran:**  3: Build, Test, Static Analysis
**Which jobs passed, which failed, and why?** All three passed


## Part 4: Integration into Dev

Provide a short summary of how you merged the branches and what problems or conflicts you encountered. 
   First I switched to Blackbox branch and created the dev branch from there. Then I started by merging Whitebox, then Review, then StaticAnalysis branches. I did not have any merge conflicts so it was able to merge correctly. After all merged and the branch diagram showed correct, I pushed branch to GitHub.


## Part 5: Verified build

**Workflow-run URL:**  
**Artifact name:**  
**Command used to run the extracted application:**  
**Did the application start successfully?**  

Why should the `package` job depend on the `test` job?

### Optional scheduled-build research (extra credit)

**Scheduled run URL:**  
**Commit that disables/removes the five-minute schedule afterward:**  

Record the cron expression you researched. Explain why the scheduled run uses the default branch, why GitHub may start it late, and why the five-minute schedule should not remain enabled:

## Part 6: Engineering reflection

Write 175–275 words. Address all six prompts:

1. Which finding or CI failure had the greatest potential impact, and why?
2. How did tests, Checkstyle, and SpotBugs provide different evidence?
3. What does the final `Dev` CI run establish, and what does it not establish?
4. What one improvement would you make to this CI process on a real team?
5. How does the packaging job prevent a ZIP with failing tests from being published?
6. Why is static analysis not a prerequisite for the packaging job in this assignment?

**Response:**


