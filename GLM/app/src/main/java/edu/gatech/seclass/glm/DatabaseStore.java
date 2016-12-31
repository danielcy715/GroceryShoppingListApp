package edu.gatech.seclass.glm;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.*;


public class DatabaseStore {

    public ArrayList<Item> items = new ArrayList<Item>();
    public StringBuilder itemTypes = new StringBuilder();


    public static DatabaseStore dbStore = new DatabaseStore();

    public static Context context;

    //Overloaded constructor for test purposes
    public DatabaseStore(){
        itemTypes.append("Fruits");
        itemTypes.append(", Cereal");
        itemTypes.append(", Alcohol");
        itemTypes.append(", Vegetables");
        itemTypes.append(", Meat");
        itemTypes.append(", Dairy");
        itemTypes.append(", Snacks");
        itemTypes.append(", FrozenFood");
        itemTypes.append(", CannedFood");
        populateDummyList();
    }

    public DatabaseStore(Context context)
    {
        this.context = context;
        itemTypes.append("Fruits");
        itemTypes.append(", Cereal");
        itemTypes.append(", Alcohol");
        itemTypes.append(", Vegetables");
        itemTypes.append(", Meat");
        itemTypes.append(", Dairy");
        itemTypes.append(", Snacks");
        itemTypes.append(", FrozenFood");
        itemTypes.append(", CannedFood");
        populateDummyList();
    }

    public static DatabaseStore getInstance(Context context) {
        //if (dbStore == null) dbStore = new DatabaseStore(context);
        if (dbStore == null) dbStore = new DatabaseStore();
        return dbStore;
    }

    public String[] getItemTypes() {
        return itemTypes.toString().split(", ");
    }

    public String[] getItemTypes1() {
        List<String> itemTypes = new ArrayList<String>();
        String[] retTypes = new String[1];
        for (Item item:items) {
            String type = item.getType();
            if (!itemTypes.contains(type)) {
                itemTypes.add(type);
            }
        }

        return itemTypes.toArray(retTypes);
    }

    public ArrayList<String> getItemUnits() {
        ArrayList<String> itemUnits = new ArrayList<>();
        for (Item item: items) {
            if (!itemUnits.contains(item.getUnit()))
                itemUnits.add(item.getUnit());
        }
        return itemUnits;
    }
/*
    public ArrayList<Item> getItemsByType() {
        Collections.sort(items, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return item1.getType().compareTo(item2.getType());
            }
        });
        return items;
    }
*/

    public ArrayList<Item> getItemsByType(String type) {

        ArrayList<Item> retItems = new ArrayList<Item>();
        for (Item item:items) {
            if (type.equals(item.getType())) {
                retItems.add(item);
            }
        }

        return retItems;
    }

    public ArrayList<Item> getSimilarItem(String name)
    {
        ArrayList<Item> similarItemList = new ArrayList<Item>();
        ArrayList<Item> items = getItemsByName();
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                similarItemList.add(items.get(i));
        }
        return similarItemList;
    }

    public ArrayList<Item> getItemsByName() {
        Collections.sort(items, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return item1.getName().compareTo(item2.getName());
            }
        });
        return items;
    }

    public void addItem(Item itemToAdd) {
        items.add(itemToAdd);
    }

    public boolean addItem(String name, String type, String unit, double quant, boolean isSelected, boolean isChecked) {
        if (!isValidItemType(type))
            return false;
        Item itemToAdd = new Item(name, type, unit, quant, isSelected, isChecked);
        items.add(itemToAdd);
        return true;
    }

    // helper method
    private boolean isValidItemType(String type)
    {
        boolean valid = false;
        String[] types = getItemTypes();
        for (int i = 0; i < types.length; i++)
        {
            if ( types[i].equals(type))
                valid = true;
        }
        return valid;
    }



    public void populateDummyList() {
        //addItemsFromFile();
        // need to set correct file path

        addItem("Apple", "Fruits", "Nos", 2, false, false);
        addItem("Banana", "Fruits", "Nos", 2, false, false);
        addItem("Orange", "Fruits",  "Nos", 2, false, false);
        addItem("Pineapple", "Fruits", "Nos", 2, false, false);
        addItem("Potato", "Vegetables", "lbs", 2,false, false);
        addItem("Tomato", "Vegetables", "lbs", 2, false, false);
        addItem("Brinjal", "Vegetables", "lbs", 2, false, false);
        addItem("Cabbage", "Vegetables", "lbs", 2, false, false);
        addItem("Kix", "Cereal", "boxes", 1, false, false);
        addItem("Chicken", "Meat", "lbs", 1, false, false);
        addItem("Milk", "Dairy", "gals", 1, false, false);
        addItem("Cheetos", "Snacks", "bags", 1, false, false);
        addItem("Waffles", "FrozenFood", "servings", 1, false, false);
        addItem("ClamChowder", "CannedFood", "cans", 1, false, false);

    }

    public void addItemsFromFile() {
        String itemContent = null;
        InputStream is = this.context.getResources().openRawResource(R.raw.initial_items);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (context != null) {
                while ((itemContent = reader.readLine()) != null) {
                    String[] itemAttr = itemContent.split(", ");
                    addItem(itemAttr[0], itemAttr[1], itemAttr[2], Double.parseDouble(itemAttr[3]),
                            Boolean.parseBoolean(itemAttr[4]), Boolean.parseBoolean(itemAttr[5]));
                }
            }
        }
        catch (IOException e) {
            System.out.println("IOException caught from trying to open initial_items.txt");
        }
        finally {
            try {
                is.close();
            }
            catch (Throwable ignore) {
            }
        }
    }
}
