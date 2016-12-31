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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    ArrayList<Item> itemList = null;
    ListView listView;
    int selglPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        selglPosition = intent.getIntExtra("position", -1);
        if (selglPosition == -1){
            setTitle("Grocery Item List");
        }
        else {
            setTitle(MainActivity.groceryListMgr.get(selglPosition).getName());
            itemList = MainActivity.groceryListMgr.get(selglPosition).itemList;
            Collections.sort(itemList);
            //initialize, unselect previous all items
            for (Item item : itemList){
                item.setSelected(false);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.listView);
        CheckBox selectAll = (CheckBox) findViewById(R.id.selectall);
        CheckBox checkAll = (CheckBox) findViewById(R.id.checkall);
        displayListView();

        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (itemList != null){
                        for (Item item : itemList){
                            item.setSelected(isChecked);
                        }
                        listView.setAdapter(dataAdapter);
                        MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
            }
        });
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (itemList != null){
                    for (Item item : itemList){
                        item.setChecked(isChecked);
                    }
                    listView.setAdapter(dataAdapter);
                    MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_search) {
            Intent intent = new Intent(ListActivity.this, SearchItemNameActivity.class);
            intent.putExtra("position", selglPosition);
            startActivity(intent);
        }

        if (id == R.id.action_add_by_type) {
            Intent intent = new Intent(ListActivity.this, ViewItemTypeActivity.class);
            intent.putExtra("position", selglPosition);
            startActivity(intent);
        }


        if (id == R.id.action_deleteAll) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete All Lists");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemList.clear();
                    listView.setAdapter(dataAdapter);
                    MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }

        if (id == R.id.action_delete_selected) {
            boolean toDelete = false;
            for (Item i : itemList){
                if (i.isSelected()){
                    toDelete = true;
                }
            }
            if (!toDelete){
                Context context = getApplicationContext();
                CharSequence text = "Please select at least one list for deletion";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete Selected Items");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = itemList.size() - 1; i >= 0; i--) {
                            if (itemList.get(i).isSelected() == true) {
                                itemList.remove(i);
                            }
                        }
                        Collections.sort(itemList);
                        listView.setAdapter(dataAdapter);
                        MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayListView(){

        Item item;
        if (itemList == null){
            itemList = new ArrayList<Item>();
/*            item = new Item("apple", "fruit","lb", 2.0, false, false);
            itemList.add(item);
            item = new Item("banana", "fruit","lb", 2.0, false, false);
            itemList.add(item);
            item = new Item("toothbrush", "oral","", 2.0, false, false);
            itemList.add(item);
            */
            //MainActivity.storeArrayVal(MainActivity.groceryListMgr, MainActivity.mainContext);
        }

        dataAdapter = new MyCustomAdapter(this,
                R.layout.item_rowinfo, itemList);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //MainActivity.storeArrayVal(itemList, getApplicationContext());
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item item = itemList.get(position);
                //showAddItemDialog(selItem);
                showEditItemDialog(item);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
        */
    }

    public void showEditItemDialog(final Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Quantity for Item: "+item.getName());
        final EditText input = new EditText(this);
        input.setId(R.id.cQty_edit_text);
        // Allow Integers only for unit which has Nos.
        if (item.getUnit().equals("Nos") || item.getUnit().length() == 0) {
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString() .equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                    return;
                }
                double qty = Double.parseDouble(input.getText().toString());
                item.setQuant(qty);
                //Item item = new Item(item.getName(),item.getType(),item.getUnit(),qty,false,false);
                //MainActivity.groceryListMgr.get(selglPosition).addItem(item);
                MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                // When clicked, jump to the list actiivity
                //Intent intent = new Intent(ViewItemNameActivity.this, ListActivity.class);
                //intent.putExtra("position", selglPosition);
                //startActivity(intent);
                listView.setAdapter(dataAdapter);
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

    private class MyCustomAdapter extends ArrayAdapter<Item> {

        //private ArrayList<Item> litemList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Item> itemList) {
            super(context, textViewResourceId, itemList);

        }

        private class ViewHolder {
            TextView name;
            TextView quantity;
            TextView unit;
            TextView type;
            CheckBox check1;
            CheckBox check2;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            Log.v("ConvertView", String.valueOf(position));
            //this.litemList = new ArrayList<Item>();
            //this.litemList.addAll(itemList);


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.item_rowinfo, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.quantity = (TextView) convertView.findViewById(R.id.quant1);
                holder.unit = (TextView) convertView.findViewById(R.id.unit1);
                holder.type = (TextView) convertView.findViewById(R.id.type);
                holder.check1 = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.check2 = (CheckBox) convertView.findViewById(R.id.checkBox2);
                convertView.setTag(holder);

                holder.check1.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        item.setSelected(cb.isChecked());
                        //MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });


                holder.check2.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        item.setChecked(cb.isChecked());
                        MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView cb = (TextView) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        //showAddItemDialog(selItem);
                        showEditItemDialog(item);

                        //MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });

                holder.quantity.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView cb = (TextView) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        //showAddItemDialog(selItem);
                        showEditItemDialog(item);

                        //MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });

                holder.unit.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView cb = (TextView) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        //showAddItemDialog(selItem);
                        showEditItemDialog(item);

                        //MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });

                holder.type.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        TextView cb = (TextView) v ;
                        // list = (List) cb.getTag();

                        Item item = itemList.get(position);

                        //showAddItemDialog(selItem);
                        showEditItemDialog(item);

                        //MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
                    }
                });


            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Item item = itemList.get(position);

            holder.name.setText(item.getName());


            holder.quantity.setText(item.getQuant());
            holder.unit.setText(item.getUnit());
            holder.type.setText(item.getType());
            holder.check1.setChecked(item.isSelected());


            holder.check1.setTag(item);
            holder.check2.setChecked(item.isChecked());
            holder.check2.setTag(item);
            MainActivity.storeArrayVal(MainActivity.groceryListMgr, getApplicationContext());
            return convertView;

        }

    }
}
