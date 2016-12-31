# Test Plan

**Author**: Team 48

**Version**: 2.0

**Summary of Changes**

 Version 2.0 : Replaced Appium with Espresso for the automation testing
 
 Version 2.0 : Updated the steps and results of existing test cases

 Version 2.0 : Added some new test cases

## 1 Testing Strategy

### 1.1 Overall strategy

- Establish test objectives
- Design criteria: correctness, feasibility, coverage, functionality
- Write test cases
- Testing test cases
- Execute test cases
- Evaluate test results

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

Test cases used will be initially drawn from use cases. 

### 1.3 Adequacy Criterion

Adequacy will be measured by how well the following questions are answered: 
- Are we building the product right (verification)?
- Are we building the right product (validation)?

### 1.4 Bug Tracking

Will use JIRA, a bug tracking product. Any issues will be filed with: title, build version, description, steps to reproduce, with attached pic/videos as deemed necessary. In addition, priority and the developer who owns the feature will be assigned.

### 1.5 Technology

For automation testing, JUnit and Espresso will be used to simulate the test cases.

JUnit is a unit testing framework for Java. Espresso is a UI test framework used to create automated UI tests for and Android app. 

## 2 Test Cases

###Grocery List Manager

Grocery List Manager Test Pre-conditions: 
- App is open to default page which is an empty grocery list

|Purpose       |Steps                                                  |Expected Result            |Actual Result|P/F info|Other|
|:-------------|:-----------------------------------------------------:|:--------------------------|:------------|:-------|:----|
|1. Create Grocery List |Press "add" menu item, populate pop-up name field, press OK  |Grocery List with name created, updated in UI| Grocery List with name created, updated in UI| Pass| |
|2. Rename Grocery List |Press "edit name" menu item for selected Grocery List, modify the name field, press OK |Selected Grocery List is renamed, shown in UI |Selected Grocery List is renamed, shown in UI|Pass||
|3. Delete Selected Grocery List(s) |Press "delete selected" menu item for selected grocery list(s), pop-up to confirm |Selected Grocery List is removed, not in UI |Selected Grocery List is removed, not in UI |Pass||
|4. Delete Selected Grocery List (Empty List Selected) |Press "delete selected" menu item for no selected grocery list, pop-up to confirm  |Error message pops-up, User is taken back to Grocery Manaager screen with no update  |Error message pops-up, User is taken back to Grocery Manaager screen with no update   |Pass||
|5. Delete All Grocery List |Press "delete all" menu item for grocery list, pop-up to confirm |All Grocery List(s) is removed, not in UI |All Grocery List is removed, not in UI |Pass||
|6. Select Grocery List |Press on the Grocery List|Grocery List is selected, the Grocery List screen for that list is shown in UI |Grocery List is selected, the Grocery List screen for that list is shown in UI|Pass||

*Cancel button for above cases will return to initial test pre-condition state

###Grocery List

Grocery List Test preconditions: 
- App is open with the default page which has two grocery lists with no items

|Purpose       |Steps                                                  |Expected Result            |Actual Result|P/F info|Other|
|:-------------|:-----------------------------------------------------:|:--------------------------|:------------|:-------|:----|
|7. Add Item from dropdown| Press the "add by type" menu item, click on desired item type, item name and type in desired quantity, press OK button to confirm|Item is successfully added to Grocery List, updated in UI |Item is successfully added to Grocery List, updated in UI |Pass||
|8a. Add Item from search |Press the "search" menu item, type in item, press search button, results will populate, user selects the desired item| Item is successfully added to Grocery List, updated in UI|Item is successfully added to Grocery List, updated in UI|Pass||
|8b. Add new Item from search (successful)|If no results from 8a, pop-up with proposed item name, unit field, quantity and item type will ask the user to populate the fields and if they want to add the item to the database. User adds valid input and confirms. |Item is successfully added to Grocery List, updated in UI|Item is successfully added to Grocery List, updated in UI|Pass||
|9. Delete Selected Item(s) | Press the "delete selected" menu item for selected item(s), press OK button on pop-up to confirm |Selected item is removed from the Grocery List screen | Selected item is removed from the Grocery List screen|Pass||
|10. Delete All Item | Press the "delete all" menu item, press OK button on pop-up to confirm |All Item(s) is removed from the Grocery List screen |All Item(s) is removed from the Grocery List screen |Pass||
|11a. Update Item (valid) | Press the item you want to update the quantity, pop-up with quantity shows up, update the field and confirm |Item quantity is updated in the Grocery List screen|Item quantity is updated in the Grocery List screen|Pass||
|11b. Update Item (invalid)| Press the item you wnat to update the quantity, pop-up with quantity shows up, update the field with invalid (non-int) value and confirm | Error message pops-up, User is taken back to Grocery List screen with no update |Error message pops-up, User is taken back to Grocery List screen with no update|Pass||

*Cancel button for above cases will return to initial test pre-condition state

|Purpose       |Steps                                                  |Expected Result            |Actual Result|P/F info|Other|
|:-------------|:-----------------------------------------------------:|:--------------------------|:------------|:-------|:----|
|12. Check Item | Press the checkbox next to the item | The Item checked attribute and UI is updated |The Item checked attribute and UI is updated|Pass||
|13. Check All Items | Press the All Items checkbox (initially blank) | All items checked attribute are True and UI is updated |All items checked attribute are True and UI is updated|Pass||
|14. Clear All Items | Press the All Items checkbox (initially checked) | All items checked attribute are False and UI is updated |All items checked attribute are False and UI is updated|Pass||
|15. Back Button | Press the back button, user is taken to Grocery List Manager Screen |The Grocery List Manager screen is on display on the UI |The Grocery List Manager screen is on display on the UI|Pass||
