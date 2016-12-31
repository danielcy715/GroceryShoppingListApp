1.	A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
	1.	To realize this requirement, I added to the design a class User, a class GroceryList, a class Item, and a class ItemDatabase. The class user has an operation buyCheckedItemsInList which takes a GroceryList parameter. The class ItemDatabase has attribute uniqueItems, implicitly searches the database for an item and has operations to add/delete/change quantity of Items to an existing GroceryList
2.	The application must contain a database (DB) of items and corresponding item types.
	1.	To realize this requirement, the class ItemDatabase has Items (has-a relationship), the class Item has an ItemType (has-a relationship).  
3.	Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
	1.	To realize this requirement, I added to the design a few operations: the User has the operation useItemDatabaseUI which uses the ItemDatabase class operation addItemToList takes as parameters the GroceryList, the ItemType, Item, and quantity. The Items displayed come from the searchItemInDatabaseHelper.
4.	Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	1.	To realize this requirement, I added to the design an additional parameter to the helper operation searchItemInDatabaseHelper; a String can be taken as a parameter and either returns the desired item, or calls another operation addItemToDatabase. AddItemToDatabase takes String and ItemType as parameters to add another unique Item to the ItemDatabase uniqueItems attribute
5.	Lists must be saved automatically and immediately after they are modified.
	1.	To realize this requirement, I added to the design an operation in GroceryList, SaveItemsToListDatabase, that detects operations that modify GroceryList attributes, and saves the changes to the ListDatabase 
6.	Users must be able to check off items in a list (without deleting them).
	1.	To realize this requirement, I added to the design an action class CheckOff which has a toggleItem operation that uses the Item key to toggle the “checked” boolean in the GroceryList mapOfItems attribute
7.	Users must also be able to clear all the check-off marks in a list at once.
	1.	To realize this requirement, I added to the design an operation in the CheckOff action takes GroceryList as a parameter and toggles the checked Boolean for each item in the mapOfItems attribute
8.	Check-off marks for a list are persistent and must also be saved immediately.
	1.	To realize this requirement, using the same method in requirement 5 the GroceryList class operation SaveItemsToListDatabase detects any changes in its state and saves it to the ListDatabase class
9.	The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
	1.	To realize this requirement, I added to the design an operation displayItemsByType to the GroceryList class which updates the View when the user accesses the GroceryList from the ListDatabase
10.	The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
	1.	To realize this requirement, I added to the design an attribute listOfGroceryLists in the ListDatabase class. The class has operations: createList, renameList, selectList, and deleteList
11.	The User Interface (UI) must be intuitive and responsive.
	1.	Not considered because it does not affect the design directly

