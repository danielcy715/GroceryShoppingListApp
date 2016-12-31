package edu.gatech.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AddItemActivity extends AppCompatActivity {
    // Define elements in this activity
    EditText nameText;
    Spinner typeSpinner;
    Spinner unitSpinner;
    EditText quantityText;

    int selglPosition = -1;
    String selSearchName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        Intent intent = getIntent();
        selglPosition = intent.getIntExtra("position", -1);
        selSearchName = intent.getStringExtra("selSearchName");

        // find element values by ID
        nameText = (EditText) findViewById(R.id.NameText);
        quantityText = (EditText) findViewById(R.id.QuantityText);
        nameText.setText(selSearchName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addItemsOnSpinners();

        Button addButton = (Button) findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = nameText.getText().toString();
                String typeValue = typeSpinner.getSelectedItem().toString();
                String unitValue = unitSpinner.getSelectedItem().toString();
                if (quantityText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                    return;
                }
                Double quantityValue = Double.parseDouble(quantityText.getText().toString());
                // set search text in the item name field

                // Define intent, pass info and start ListActivity

                Item item = new Item(nameValue,typeValue,unitValue,quantityValue,false,false);
                DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
                dbStore.addItem(item);
                MainActivity.storeDatabase(DatabaseStore.dbStore, getApplicationContext());
                MainActivity.groceryListMgr.get(selglPosition).addItem(item);
                Collections.sort(MainActivity.groceryListMgr.get(selglPosition).itemList);
                MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());

                // When clicked, jump to the list activity
                Intent intent = new Intent(AddItemActivity.this, ListActivity.class);
                intent.putExtra("position", selglPosition);
                startActivity(intent);

            }
        });
    }

    // add items into spinner dynamically
    public void addItemsOnSpinners() {
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        unitSpinner = (Spinner) findViewById(R.id.unitSpinner);

        DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
        String[] itemTypes = dbStore.getItemTypes();
        ArrayList<String> itemUnits = dbStore.getItemUnits();

        // Convert itemType String Array to List object
        List<String> itemTypeList = new ArrayList<String>();
        for (String item: itemTypes) {
            itemTypeList.add(item);
        }

        // Set spinner for type
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemTypeList);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapterType);

        // Set spinner for unit
        ArrayAdapter<String> dataAdapterUnit = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemUnits);
        dataAdapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(dataAdapterUnit);
    }
}

