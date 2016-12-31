package edu.gatech.seclass.glm;

/**
 * Team 48 Project
 */

import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;


import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest
        extends ActivityUnitTestCase<MainActivity> {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private String newGList1, newGList2, newGList3, newGList4, renameGList;
    private String search1, search2;
    private String nItemName, nItemType, nItemUnit;
    private int qtyOrange, qtyApple, qtyCheetos, qtyPineapple, nItemQty;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Before
    public void initItems() {
        newGList1 = "Laura's List";
        newGList2 = "Gopal's List";
        newGList3 = "Thien's List";
        newGList4 = "Daniel's List";
        renameGList = "Daniel's new List";

        nItemName = "Beef";
        nItemType = "Meat";
        nItemUnit = "lbs";

        nItemQty = 4;
        qtyOrange = 3;
        qtyPineapple = 5;
        qtyApple = 7;
        qtyCheetos = 3;

        search1 = "App";
        search2 = "To";
    }


    //Test for add newGroceryList
    @Test
    public void test01AddGList() {
        //Add 4 new GroceryList
        openContextualActionModeOverflowMenu();
        onView(withText("Add"))
                .perform(click());
        onView(withId(R.id.my_edit_text))
                .perform(typeText(newGList1), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Add"))
                .perform(click());
        onView(withId(R.id.my_edit_text))
                .perform(typeText(newGList2), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Add"))
                .perform(click());
        onView(withId(R.id.my_edit_text))
                .perform(typeText(newGList3), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Add"))
                .perform(click());
        onView(withId(R.id.my_edit_text))
                .perform(typeText(newGList4), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test four new GroceryLists are created
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText(newGList1)));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText(newGList2)));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.name))
                .check(matches(withText(newGList3)));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(3).onChildView(withId(R.id.name))
                .check(matches(withText(newGList4)));
    }


    //Test for rename newGroceryList
    @Test
    public void test02RenameGList() {
        //rename Daniel's GroceryList
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(3)
                .onChildView(withId(R.id.checkBox1))
                .perform(click());
        openContextualActionModeOverflowMenu();
        onView(withText("Edit Name"))
                .perform(click());
        onView(withId(R.id.my_edit_text))
                .perform(typeText(renameGList), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test that Daniels's List has been renamed
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(3).onChildView(withId(R.id.name))
                .check(matches(withText(renameGList)));
    }


    //Test for attempting delete without item selection
    @Test
    public void test03ErrorDeleteWithoutSelecting() {
        //Try Deleting without selecting items
        openContextualActionModeOverflowMenu();
        onView(withText("Delete Selected"))
                .perform(click());

        //Test that toast message is displayed
        onView(withText("Please select at least one list for deletion"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    //Test for deleteSelectedGroceryList
    @Test
    public void test04DeleteSelected() {
        //Delete Laura and Thien's list
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.checkBox1))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.checkBox1))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Delete Selected"))
                .perform(click());
        onView(withText("Yes"))
                .perform(click());

        //Test that Selected Lists have been deleted
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText(newGList2)));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText(renameGList)));
    }


    //Test for addItemByType
    @Test
    public void test05AddItemByType() {
        //Add  5 Pineapple and 3 Orange to Gopal's List
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .perform(click());
        openContextualActionModeOverflowMenu();
        onView(withText("Add By Type"))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vtlistView))
                .atPosition(0)
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vnlistView))
                .atPosition(2)
                .perform(click());
        onView(withId(R.id.qty_edit_text))
                .perform((typeText(String.valueOf(qtyOrange))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Add By Type"))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vtlistView)).atPosition(0)
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vnlistView)).atPosition(3)
                .perform(click());
        onView(withId(R.id.qty_edit_text))
                .perform((typeText(String.valueOf(qtyPineapple))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test that Selected Items have been added
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText("Orange")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText("Pineapple")));
    }


    //Test for deleting all items on List
    @Test
    public void test06DeleteAllListItems() {
        //Delete All the added items previously added
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .perform(click());
        openContextualActionModeOverflowMenu();
        onView(withText("Delete All"))
                .perform(click());
        onView(withText("Yes"))
                .perform(click());

        //Test that all Items have been deleted
        onView(withId(R.id.name))
                .check(doesNotExist());
    }


    //Test for Back Button
    @Test
    public void test07GoBack() {
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .perform(click());
        onView(withContentDescription("Navigate up"))
                .perform(click());

        //Test that you have gone to the previous view
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText(newGList2)));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText(renameGList)));
    }


    //Test for addItemByName
    @Test
    public void test08AddItemByName() {
        //Add 7 apples, 3 Cheetos and 5 Pineapples to Daniel's new List
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        openContextualActionModeOverflowMenu();
        onView(withText("Search"))
                .perform(click());
        onView(withId(R.id.searchText))
                .perform((typeText(search1)), closeSoftKeyboard());
        onView(withId(R.id.buttonSearch))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vnlistView))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.qty_edit_text))
                .perform((typeText(String.valueOf(qtyApple))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Search"))
                .perform(click());
        onView(withId(R.id.searchText))
                .perform((typeText(search2)), closeSoftKeyboard());
        onView(withId(R.id.buttonSearch))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vnlistView)).atPosition(0)
                .perform(click());
        onView(withId(R.id.qty_edit_text))
                .perform((typeText(String.valueOf(qtyCheetos))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Search"))
                .perform(click());
        onView(withId(R.id.searchText))
                .perform((typeText(search1)), closeSoftKeyboard());
        onView(withId(R.id.buttonSearch))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.vnlistView))
                .atPosition(1)
                .perform(click());
        onView(withId(R.id.qty_edit_text))
                .perform((typeText(String.valueOf(qtyPineapple))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test three new items are created
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText("Apple")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText("Pineapple")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.name))
                .check(matches(withText("Cheetos")));
    }


    //Test for adding null as quantity
    @Test
    public void test09AddWrongQuantity() {
        //Add wrong quantity for item
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .perform(click());

        onView(withId(R.id.cQty_edit_text))
                .perform((typeText("")), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test that toast message is displayed
        onView(withText("Please enter quantity"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    //Test for editing quantity
    @Test
    public void test10EditQuantity() {
        //edit quantity for item
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .perform(click());

        onView(withId(R.id.cQty_edit_text))
                .perform((typeText(String.valueOf(qtyApple))), closeSoftKeyboard());
        onView(withText("OK"))
                .perform(click());

        //Test that the right quantity is displayed
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.quant1))
                .check(matches(withText("7")));
    }


    //Test for addItemByNameFailed
    @Test
    public void test11AddItemByNameFailed() {
        //Add 5 lbs of Meat to Daniel's new List
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        openContextualActionModeOverflowMenu();
        onView(withText("Search"))
                .perform(click());
        onView(withId(R.id.searchText))
                .perform((typeText(nItemName)), closeSoftKeyboard());
        onView(withId(R.id.buttonSearch))
                .perform(click());

        onView(withId(R.id.typeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(nItemType)))
                .perform(click());

        onView(withId(R.id.unitSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(nItemUnit)))
                .perform(click());

        onView(withId(R.id.QuantityText))
                .perform((typeText(String.valueOf(nItemQty))), closeSoftKeyboard());

        onView(withText("Add Item To List"))
                .perform(click());

        //Test that Beef is added to list
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.name))
                .check(matches(withText(nItemName)));
    }


    //Test for checkAllItems
    @Test
    public void test12CheckAllItems() {
        //Check all items on Daniel's new list
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onView(withResourceName("checkall"))
                .perform(click());

        //Test all items are checked
        onView(withResourceName("checkall"))
                .check(matches(isChecked()));
    }


    //Test for clearAllItems
    @Test
    public void test13ClearAllItems() {
        //Clear all items on Gopal's list
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onView(withResourceName("checkall"))
                .perform(click());
        onView(withResourceName("checkall"))
                .perform(click());

        //Test all items are cleared
        onView(withResourceName("checkall"))
                .check(matches(isNotChecked()));
    }


    //Test for checkItem
    @Test
    public void test14CheckItem() {
        //Check Apple and Pineapple on Daniel's new list
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.checkBox2))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.checkBox2))
                .perform(click());

        //Test selected items are checked
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.checkBox2))
                .check(matches(isChecked()));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2).onChildView(withId(R.id.checkBox2))
                .check(matches(isChecked()));
    }


    //Test for delete selected list items
    @Test
    public void test15DeleteSelectedListItem() {
        //Delete Apple and Pineapple from Daniel's New List
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.checkBox1))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.checkBox1))
                .perform(click());

        openContextualActionModeOverflowMenu();
        onView(withText("Delete Selected"))
                .perform(click());
        onView(withText("Yes"))
                .perform(click());

        //Test selected items are deleted
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).onChildView(withId(R.id.name))
                .check(matches(withText("Beef")));
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1).onChildView(withId(R.id.name))
                .check(matches(withText("Cheetos")));

    }


    //Test for deleteAllGroceryList
    @Test
    public void test16DeleteAll() {
        //Delete all GroceryLists initialised
        openContextualActionModeOverflowMenu();
        onView(withText("Delete All"))
                .perform(click());
        onView(withText("Yes"))
                .perform(click());

        //Test that all grocery lists are deleted
        onView(withId(R.id.item))
                .check(doesNotExist());
    }

}