# Design information for GroceryListManager

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
 * Defined a **Item** Class for an item.
 * Added attribute ***qty*** and ***unit*** in the **Item** class.
 * Defined a **GroceryList** class to represent the Grocery List.
 * Added an aggregated association between **Item** and **GroceryList** since the GroceryList contains one or multiple Items.
 * Added operation ***add, delete and update*** in the **GroceryList** class for doing add/delete and update operation. The update method also takes the quantity as the input so that it can update the list with the quantity for that item.
 * Added parmeter qantity and unit to the update operation. For eg the qty can be a integer and the unit will be unit of measurement for that item.(for eg, Pound/dozens/no etc)
2. The application must contain a database (DB) of items and corresponding item types.
 * 	Added attribute ***name*** and ***type*** to the **Item** class.
 * The Database is not modeled explicity here but I want to define a PersistentStore class which will take care of saving and retrieving items to and from the data base.
 * Since quantity is not there for the inventory of items, creating a new class **PersistentItem** which will have **'is a'** relation with the **Item** class.
 * Also created a **CheckListItem** which will have **'is a'** relation with the **Item** class. Moved the ***qty*** and ***unit*** to **CheckListIem**.
 *  Moved the aggregated association between **Item** and **GroceryList** to **CheckListItem** and **GroceryList** since we can only have the **CheckListItem** inside the GroceryList.
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
  * The first part is more of a UI requirement on how the list will be displayed to the user. I am assuming that the heirarchial list is progressively disclosed where they drill down on the type to get the list of item names for that item type.
  * I have added a get operation ***getItemTypes*** in **PersistentStore** which will return all the item types in the Database as a string so that UI can display the types 
  * Added get operation ***getItemsByType*** in **PersistentStore** which will accept the type of the item as the parameter and will return the list of all the items for that type. Once user select a item type, the UI can call this method to get the items. Here I am returning Item array rather than String of item names because we might need to use that item to add it into the list. 
4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	* Once user types the name since it needs to fetch the item by name added the operation **getItemsByName** in the **PersistenceStore** class to look for items that matches the name. This will accept the parameter **'name'** that is typed by the user. This will return the list of items back to the user alone with its name and type. 
	* Added operation **getItemTypes** to **PersistenceStore** so that, if user does not find the match, then UI can display all the available types and ask user to pick up the type for the new item.
	* Added operation **addItem**  to **PersistentStore** so that UI can call the method **addItem** by passing the name and type as parameter to add the list in to the Database.
	* If the list need to be added as part of the GroceryList then the UI can call explcit operation to add the item to the List.
5. Lists must be saved automatically and immediately after they are modified.
  * This requirement is on how the APIs will be called from the UI. This is not considered in the class design since it does not affect the design.
6. Users must be able to check off items in a list (without deleting them).
	* There should be a attribute **isChecked** on a item. Since this is applicable only for CheckListItem, added the attribute in the **CheckListItem** class.
7. Users must also be able to clear all the check-off marks in a list at once.
	* Adding operation **clearAllChecked** in **GroceryList** which can clear all the checked-off markes that are there in the list.
8. Check-off marks for a list are persistent and must also be saved immediately.
	* This requirement is on how the APIs will be called from the UI. The API to ***setChecked*** needs to be called on the **CheckListItem** when checkbox is clicked on the UI. This is not considered in the class design.
9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
	* Added operation **getItemTypes** and **getItemsByType** on the GroceryList so that it can can be used by UI get the list of Items types and the items to be displayed by the UI.
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
	* Since we want to support multiple List I defined a class **GroceryListManager** which has a contains relationship with the **GroceryList** so that we can support multiple list at a time
	* Also Added a attribute **name** to the Grocery List to identity the list.
	* Added operation **create, rename and delete** to the **GroceryListManager**
	* Added an aggregated association between **GroceryListManager** and **GroceryList** since the GroceryListManager contains multiple GroceryList.
11. The User Interface (UI) must be intuitive and responsive.
	* Not considered since it is not part of a structural design and does not affect the design directly.

###Notes

1. Getters and setters not given for all attributes