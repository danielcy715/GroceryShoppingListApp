package edu.gatech.seclass.glm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gopalramanujam on 10/10/16.
 */
public class GroceryList {

    private String name;
    private boolean isSelected;
    public ArrayList<Item> itemList;
    //DatabaseStore dbs = DatabaseStore.getInstance(getApplicationContext());

    public GroceryList(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
        this.itemList = new ArrayList<Item>();

    }

    public String[] getItemTypes() {

        return null;
    }
/*
    public CheckListItem[] getItemsByType(String type) {
        CheckListItem[] retArray = new CheckListItem[0];
        ArrayList<CheckListItem> retList = new ArrayList<CheckListItem>();
        for (CheckListItem checkItem:checkListItemList) {
            if (checkItem.getType().equals(type)) {
                retList.add(checkItem);
            }
        }
        return retList.toArray(retArray);
    }*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    /* // Copied to DBStore
    public ArrayList<Item> similarItem(String name)
    {
        ArrayList<Item> similarItemList = new ArrayList<Item>();
        ArrayList<Item> items = dbs.getItemsByName();
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getName().contains(name))
                similarItemList.add(items.get(i));
        }
        return similarItemList;
    }
    */

    public void addItem(Item newItem) {
        boolean itemMatched = false;
        if (itemList == null) itemList = new ArrayList<Item>();
        for (Item item : itemList) {
            if (item.getName().equals(newItem.getName()) && item.getType().equals(newItem.getType())) {
                double qty = Double.parseDouble(item.getQuant()) + Double.parseDouble(newItem.getQuant());
                item.setQuant(qty);
                itemMatched = true;
            }
        }
        if (!itemMatched) itemList.add(newItem);
    }
}