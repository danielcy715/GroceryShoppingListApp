# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: \<person or team name\>

Main content derived from: groups.engin.umd.umich.edu/CIS/course.des/cis375/ppt/lec25.ppt

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

Establish test objectives
Design criteria: correctness, feasibility, coverage, functionality
Write test cases
Testing test cases
Execute test cases
Evaluate test results

Unit Testing
- Interfaces tested for proper information flow
- Boundary conditions, error paths tested

Integration Testing
- Top down: have main program as driver and use stubs for components; test as components are developed and integrated
- Bottom up: components are combined into functional clusters and tested

System Testing
- Recovery testing: find failure scenarios and handle them
- Security testing: can GroceryList or ItemDatabase information be illegally modified
- Stress testing: test limits - number of items, lists
- Performance testing: test search, modify, delete times; how long it takes to perform actions

Regression Testing
- With full set of test cases, testing can be separated into Manual and Automated
- Automation framework for functional testing
- Manual testers to run remaining test cases
- Results will be compared with expected to catch SW defects from SW changes
- Report for each new build

### 1.2 Test Selection

*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

Test cases used will be initially drawn from use cases. 

### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

Adequacy will be measured by how well the following questions are answered: 
- Are we building the product right (verification)?
- Are we building the right product (validation)?

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

Will use JIRA, a bug tracking product. Any issues will be filed with: title, build version, description, steps to reproduce, with attached pic/videos as deemed necessary. In addition, priority and the developer who owns the feature will be assigned.

### 1.5 Technology

*Describe any testing technology you intend to use or build (e.g., JUnit, Selenium).*

For automation testing, JUnit and Appium will be used to simulate the test cases.

JUnit is a unit testing framework for Java. Appium is a portable software testing framework based off of Selenium, for mobile testing. 

## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*

Test preconditions: App is open to default page with a default grocery list, with 1 default Item populated

Purpose                 Steps Necessary                                                         Expected Result     Actual Result       P/F
Grocery List Manager
- Create Grocery List   Press the + button, populate the name field of pop-up, press OK         Grocery List with name created, shown in UI
- Rename Grocery List   ... edit button for ..., modify the name field...                       Default Grocery List is renamed, shown in UI
- Delete Grocery List   Press the delete button for the default grocery list, pop-up to confirm Default Grocery List is removed, not in UI
- Select Grocery List   Press on the Grocery List                                               Grocery List is selected

*Cancel button for above cases will return to previous state

Test preconditions: App is open with the default grocery list selected

Grocery List
- Add Item              Press the + button, pop up has 3 fields: item type, item and quantity, select item type from 1st dropdown, then select item from 2nd dropdown, press OK button to confirm
*Case where not all fields are populated, show error message
- Search Item           Click on search field, type in item, press search button, results will populate, select, same screen for Add Item appears with the first 2 fields populated.
*If no results, pop-up will ask the user if they want to add the item to the database. If OK, item type is specified from dropdown list and asked to confirm. If confirmed, the item is added, and the user is taken to the Add Item screen with the first 2 fields populated 
- Delete Item           Press the delete button, press OK button on pop-up to confirm
- Update Item           Press the update button, pop-up with quantity shows up, update the field and confirm

*Cancel button for above cases will return to previous state

- Check Item            Press the checkbox next to the item, the Item checked attribute and UI is updated
- Check All Items       Press the All Items checkbox (initially blank), all items checked attribute are True and UI is updated
- Clear All Items       Press the All Items checkbox (initially checked), all items checked attribute are False and UI is updated

- Back Button           Press the back button, user is taken to Grocery List Manager Screen

