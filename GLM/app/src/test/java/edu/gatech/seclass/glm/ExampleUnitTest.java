package edu.gatech.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Team 48 Project
 */
public class ExampleUnitTest {
    private DatabaseStore dB;

    @Before
    public void startup(){
        dB = new DatabaseStore();
    }

    @After
    public void tearDown(){
        dB = null;
    }

    @Test
    public void getItemType(){
        String[] expectedArray = {"Fruits", "Cereal","Alcohol", "Vegetables",
                "Meat", "Dairy", "Snacks", "FrozenFood", "CannedFood"};
        assertArrayEquals(expectedArray, dB.getItemTypes());
    }

    @Test
    public void getItemUnits(){
        ArrayList<String> myUnits = new ArrayList<>();
        myUnits.add("Nos");
        myUnits.add("lbs");
        myUnits.add("boxes");
        myUnits.add("gals");
        myUnits.add("bags");
        myUnits.add("servings");
        myUnits.add("cans");
        assertEquals(myUnits, dB.getItemUnits());
    }

    @Test
    public void getItemsByType(){
        ArrayList<String> expectedArray = new ArrayList<>();
        ArrayList<String> actualArray = new ArrayList<>();
        ArrayList<Item> nb = dB.getItemsByType("Fruits");

        expectedArray.add("Apple");
        expectedArray.add("Banana");
        expectedArray.add("Orange");
        expectedArray.add("Pineapple");

        for(int i = 0; i < nb.size(); i++){
            actualArray.add(nb.get(i).getName());
        }
        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void getSimilarItem(){
        ArrayList<String> expectedArray = new ArrayList<>();
        ArrayList<String> actualArray = new ArrayList<>();
        ArrayList<Item> nb = dB.getSimilarItem("Ap");

        expectedArray.add("Apple");
        expectedArray.add("Pineapple");

        for(int i = 0; i < nb.size(); i++){
            actualArray.add(nb.get(i).getName());
        }
        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void getItemsByName(){
        ArrayList<String> expectedArray = new ArrayList<>();
        ArrayList<String> actualArray = new ArrayList<>();
        ArrayList<Item> nb = dB.getItemsByName();

        expectedArray.add("Apple");
        expectedArray.add("Banana");
        expectedArray.add("Orange");
        expectedArray.add("Pineapple");
        expectedArray.add("Brinjal");
        expectedArray.add("Cabbage");
        expectedArray.add("Potato");
        expectedArray.add("Tomato");
        expectedArray.add("Kix");
        expectedArray.add("Chicken");
        expectedArray.add("Milk");
        expectedArray.add("Cheetos");
        expectedArray.add("Waffles");
        expectedArray.add("ClamChowder");
        Collections.sort(expectedArray);

        for(int i = 0; i < nb.size(); i++){
            actualArray.add(nb.get(i).getName());
        }
        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void addItem(){
        dB.addItem("Beef", "Meat", "lbs", 3, false, false);
        ArrayList<String> actualArray = new ArrayList<>();
        ArrayList<Item> nb = dB.getItemsByName();

        for(int i = 0; i < nb.size(); i++){
            actualArray.add(nb.get(i).getName());
        }
        assertTrue(actualArray.contains("Beef"));
    }

}