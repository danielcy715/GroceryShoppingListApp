#design information
1.A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
Create grocerylist class, allow grocerylist to create, delete and update items as operations. The grocerylist has item and name as attributes. Also create user class and item class. 
2.The application must contain a database (DB) of items and corresponding item types.
Create database class. It has item name and item type as attributes.
3,Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
Add name and type as attributes of item.
4.Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.
Add searchItem operation so that database is able to search the item name in the database. Add addItem operation to allow add new item name and types. 
5.Lists must be saved automatically and immediately after they are modified.
List is a class here so it has dedicated storage and any change can be reflected immediately
6.Users must be able to check off items in a list (without deleting them).
Add isChecked attribute to item class. Also add checkItem as operation in the grocerylist class so that the individual item can be checked.
7.Users must also be able to clear all the check-off marks in a list at once.
Add checkAll and clearAllChecked operation to allow list to check off all itms or clear all check-off marks.
8.Check-off marks for a list are persistent and must also be saved immediately.
The isChecked attribute will be able to show the state immediately.
9.The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
Add groupItem operation. It will allow items to be grouped by type.
10.The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
Add groceryListManager class. It has grocerylist class as attributes. It has add, delete, rename list operations. 
11.The User Interface (UI) must be intuitive and responsive.
Not considered because it does not affect the design directly.

