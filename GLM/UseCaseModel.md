# Use Case Model


**Author**: Team 48

## 1 Use Case Diagram

![Team Use Case Design](UseCaseDiagram.png)

## 2 Use Case Descriptions

Below are the use case descriptions:

###Create Grocery List
- Requirements: This should allow the user to create a GroceryList item
- Pre-conditions: There is add list button
- Post-conditions: GroceryList item is added and can be seen from UI
- Scenario:
	- User opens GroceryListManager screen
	- User clicks on add button
	- GroceryList item is added to the UI

###Select Grocery List
- Requirements: This should allow the user to select a GroceryList item
- Pre-conditions: The GroceryList item to be selected must exit
- Post-conditions: The GroceryList item is selected and the box is checked
- Scenario:
	- User opens GroceryListManager screen
	- User clicks on the Grocerylist item to be selected
	- GroceryList item is selected

###Rename Grocery List
- Requirements: This should allow the user to rename a GroceryList item
- Pre-conditions: The GroceryList item to be renamed must exist
- Post-conditions: The GroceryList item is renamed 
- Scenario:
	- User opens GroceryListManager screen
	- User clicks on the GroceryList item to be renamed
	- User types the new name for the GroceryList item
	- GroceryList item is renamed

###Delete GroceryList:
- Requirements: This should allow the user to delete a GroceryList item
- Pre-conditions: The GroceryList item to be deleted must exist
- Post-conditions: GroceryList selected must be deleted from memory, and removed from the UI
- Scenario: 
	- User opens GroceryListManager screen from the title screen, or from back button on a GroceryList screen
	- User selects GroceryList to be deleted
	- User clicks on delete button
	- GroceryList(s) is deleted from memory, and removed from the UI

###Add Item via 1. Search 2. Menu Hierarchy 3. New Item
- Requirements: This should allow the user to add an Item to an existing GroceryList
- Pre-conditions: User selects a GroceryList and the GroceryList Detail screen is active
- Post-conditions: Table of Items on the GroceryList Detail screen should be updated with a new row with Item Name, Type, Quantity and Checked
- Scenarios: 
	- User initiates action to add new Item to the existing Grocery List (by pressing the Add button)
	- User is presented 3 ways to add the item, via search or dropdown menu or Add New Item option and selects one
	- Scenario 1: Adding Item via search
		- User is taken to a new screen presenting a search field with a table of Search Results
		- User enters a string into the search field
		- User presses the search button
		- The search results become updated with name matches, displaying the item type and an option to select the item
		- User selects an item from the results
	- Scenario 2: Adding Item via dropdown
		- User is taken to a new screen with 2 initially blank fields: Item Type dropdown, and Item Name dropdown
		- User presses on the Item Type field
		- A dropdown menu is presented with the various Item Types
		- The User presses on the desired item type
		- The Item Name dropdown is updated with items in the Item Type category
	- Scenario 3: Adding New Item
		- User is presented a Screen with an ItemType dropdown, 2 textfields for Item Name and Unit
		- User populates the textFields with valid string inputs
		- User selects an ItemType from the dropdown
		- Presses the confirm button
			- a) If invalid string inputs, a pop-up will show the corresponding error, and user does not proceed
			- b) If valid string inputs, Item name is added to the Item Database

###Delete Item
- Requirements: This should allow the user delete an item from the GroceryList
- Pre-conditions: An item to be deleted must exist and must be selected
- Post-conditions: Item selected must be deleted from the list
- Scenerio: 
	- User opens new list or existing list with item(s) on it
	- User selects item(s) to be deleted
	- User clicks on delete button
	- Item(s) is deleted from the list

###Update Item
- Requirements: This should allow the user to update quantity of an item on the GroceryList
- Pre-conditions: The item quantity pair must already exist on the GroceryList
- Post-conditions: Quantity of list should be updated to whatever quantity is entered or selected
- Scenerio: 
	- User opens new list or existing list with item quantity pair on it
	- User selects item whose quantity is to be modified
	- User clicks on update item and inputs or selects desired quantity
	- Quantity is modified on list

###Check Item
- Requirements: This should allow the user to check or uncheck item(s) on the GroceryList
- Pre-conditions: Item(s) to be checked or unchecked must already exist on the list
- Post-conditions: Selected item(s) should have their boxes checked or unchecked
- Scenerio 1: Check Item
	- User opens new list or existing list with unchecked items on it
	- User clicks on the checkbox of individual item(s)
	- Item(s) selected is checked                
- Scenerio 2: Uncheck Item 
	- User opens new list or existing list with checked items on it
	- User clicks on the checkbox of individual item(s)
	- Item(s) selected is unchecked

###Check All Items
- Requirements: This should allow the user to check All the items at once on the GroceryList
- Pre-conditions: Item(s) must already exist on the list
- Post-conditions: All Items should have their boxes checked
- Scenerios: 
	- User opens new list or existing list with unchecked items on it
	- User clicks on the checkbox in the Header checkbox column to check all items in one shot
	- All Item(s) selected are checked

###Clear All Items
- Requirements: This should allow the user to Clear All the checked items at once on the GroceryList
- Pre-conditions: Item(s) must already exist on the list
- Post-conditions: All Items should have their boxes unchecked
- Scenerios: 
	- User opens new list or existing list with checked items on it
	- User clicks on the checkbox in the Header checkbox column to clear the checkbox and it should clear All items in one shot
	- All Item(s) selected are cleared and no Item(s) should be checked after clearing.

