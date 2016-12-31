package edu.gatech.seclass.glm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ViewItemNameActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter = null;
    ArrayList<Item> items;
    ListView listView;
    //Selected GroceryList Position
    int selglPosition=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_name);
        listView = (ListView) findViewById(R.id.vnlistView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        selglPosition = intent.getIntExtra("position", -1);
        String selItemType = intent.getStringExtra("selItemType");
        String selSearchName = intent.getStringExtra("selSearchName");
        if (selItemType != null){
            setTitle(selItemType);
            displayItemNamesFromType(selItemType);
        }
        else if (selSearchName != null){
            setTitle(selSearchName);
            displayItemNamesFromSearch(selSearchName);
        }
    }

    private void displayItemNamesFromType(String type) {
        items = new ArrayList<Item>();

        DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
        items = dbStore.getItemsByType(type);

        ArrayList<String> itemNames = new ArrayList<String>();
        for (Item item:items) {
            itemNames.add(item.getName());
        }
//        itemTypes.addAll(dbStore.getItemTypes1());

//        ArrayList<String> itemTypesArr = new ArrayList<String>(Arrays.asList(itemTypes));
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_view, R.id.item,itemNames);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item selItem = items.get(position);
                showAddItemDialog(selItem);

            }
        });

    }

    private void displayItemNamesFromSearch(String searchName) {
        items = new ArrayList<Item>();

        DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
        items = dbStore.getSimilarItem(searchName);

        ArrayList<String> itemNames = new ArrayList<String>();
        for (Item item:items) {
            itemNames.add(item.getName());
        }
//        itemTypes.addAll(dbStore.getItemTypes1());

//        ArrayList<String> itemTypesArr = new ArrayList<String>(Arrays.asList(itemTypes));
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_view, R.id.item,itemNames);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item selItem = items.get(position);
                showAddItemDialog(selItem);

            }
        });

    }

    private void showAddItemDialog(final Item selItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Item for "+selItem.getName() + " in " + selItem.getUnit());
        final EditText input = new EditText(this);
        input.setId(R.id.qty_edit_text);
        // Allow Integers only for unit which has Nos.
        if (selItem.getUnit().equals("Nos") || selItem.getUnit().length() == 0) {
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            //input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        }
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double qty = 0;
                if (input.getText().toString() .equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                    return;
                }
                qty = Double.parseDouble(input.getText().toString());
                Item item = new Item(selItem.getName(),selItem.getType(),selItem.getUnit(),qty,false,false);
                MainActivity.groceryListMgr.get(selglPosition).addItem(item);
                Collections.sort(MainActivity.groceryListMgr.get(selglPosition).itemList);
                MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(ViewItemNameActivity.this, ListActivity.class);
                intent.putExtra("position", selglPosition);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               int itemType, ArrayList<String> itemTypes) {
            super(context, textViewResourceId, itemType, itemTypes);

        }

    }

/*
    private class MyCustomAdapter extends ArrayAdapter<Item> {

        ArrayList<Item> lItems = null;
        private int selectedIndex = -1;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               int itemType, ArrayList<Item> items) {
            super(context, textViewResourceId, itemType, items);

        }
        private class ViewHolder {
            TextView name;
            RadioButton radio;
        }

        public void setSelectedIndex(int index) {
            selectedIndex = index;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            int convertView1 = Log.v("ConvertView", String.valueOf(position));
            this.lItems = new ArrayList<Item>();
            this.lItems.addAll(items);


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_name_row, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.radio = (RadioButton) convertView.findViewById(R.id.radio1);
                convertView.setTag(holder);

                holder.radio.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        RadioButton rb = (RadioButton) v ;
                        // list = (List) cb.getTag();

                        Item list = items.get(position);
                        setSelectedIndex(position);
                        MyCustomAdapter.this.notifyDataSetChanged();
//                        list.setSelected(rb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == selectedIndex) {
                holder.radio.setSelected ( true );
            }
            else {
                holder.radio.setSelected ( false );
            }
            Item list = items.get(position);

            holder.name.setText(list.getName());
            //holder.check.setText(list.getName());
            holder.radio.setChecked(list.isChecked());
            holder.radio.setTag(list);
//            MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());

            return convertView;

        }

    }
*/
}
