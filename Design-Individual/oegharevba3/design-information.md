## UML MODELLING OF GROCERY LIST

### 1.
A grocery list consists of items the users want to buy.  
To realise this requirement, I created two classes (**User** and **GroceryList**).  

Application must allow users to add items.. delete items.. change quantity of items.  
To realise this requirement, the User class has the following operations:  
* **addItem()** - to add items to the list  
* **deleteItem()** - to deleted Items from the list  
* **updateQuantity()** - to change the quantity of items on the list  
	

### 2.
The application must contain a database(DB) of items and corresponding item types.  
To realise this requirement, I added a class **Item** with attributes:  
* **itemType:** This is created as an enumeration and cannot be altered or edited by the user  
* **itemName:** This is the name of the item to be added  
* **measurementUnit:** This is to cater for the different unit of measurement for the various items, i.e Pounds, Kg, etc  

### 3.
Users must be able to add items to a list by picking then from a hierarchical list  
To realise this requirement:  
* **showItemList** This operation is created under the **AddItem** class, and display the items in the hierarchical format  
* **addItem(itemType, itemName)** is created in the **User** class and takes in an ItemType to find the itemType and then utilises the itemName to locate the item to be added to the list  

User must be able to specify a quantity for the item.  
For any item added to the grocery list, the attribute **quantity** has a default value of **1**. The operation **updateQuantity()** can be used to update the default quantity  

### 4.
Users must also be able to specify an item by typing its name.. together with its type  
To realise this requirement, there is an overloaded version of addItem, **addItem(itemName)** that takes in only the itemName as parameter.  
* **isItemTypeEntered:** Set to a boolean with default value, true. It tests which version of the addItem constructor is to be called.  
* **lookupDatabase():** Looks up the database for itemName typed in, outputs a list of similar names which is stored in the **matchItemList** attribute of **addItem**  
* **isRightItem():** This operation matches each item in the **matchItemList** to the itemName inputted. If a match is found, the matched item is added to the **GroceryList**  
* **selectItemType():** If **isRightItem()** is false, user uses this operation to select an itemType for the itemName initially inputted  
* **saveNewItem():** This saves the newly created itemName under the selected itemType and adds item to the **Item** class and also adds this item to the **GroceryList**  

### 5.
Lists must be saved automatically and immediately after they are modified  
To realise this requirement, two operations are created in the **Grocerylist** class  
* **isListModified():** This would be a listener that would call the save operation for any modification done on the List.  
* **saveGroceryList():** This operation saves the list immediately it is called.  

### 6.
Users must be able to check off items in a list (without deleting them)  
To realise this requirement:  
* **checkOffItem():** This is an operation in the **User** class that is used to check off individual items on the list.  

### 7.
Users must be able to clear all the check-off marks in a list at once  
To realise this requirement:  
* **resetCheckOffMark():** This is an operation in the **User** class that is used to reset the state of all the checkOffMarks on the list to its default value (false).  

### 8.
Check-off marks for a list are persistent and must also be saved immediately  
To realise this requirement, two operations are in the **Grocerylist** class  
* **isCheckOffChanged():** This would be a listener that would call the save operation for any modification done to the check-off.  
* **saveGroceryList():** This operation saves the list immediately it is called.  


### 9.
The application must present the items in a list... and forth between aisles.  
To realise this requirement, **showItem()** is an operation in the **Item** class, that shows all items grouped under its corresponding **itemType**  



### 10.
The application must support mutliple lists at a time.... and delete lists.  
To realise this requirement, the following operations are created in the **User** class:  
* **createNewGroceryList:** This operation creates a new Grocery List  
* **renameGroceryList:** This operation renames an existing Grocery List  
* **selectGroceryList:** This operation selects an existing Grocery List  
* **deleteGroceryList:** This operation deletes an existing Grocery List  

### 11.
The User Interface (UI) must be intuitive and responsive  
Not considered because it does not affect the design directly  