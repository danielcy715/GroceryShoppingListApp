package edu.gatech.seclass.glm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    public static ArrayList<GroceryList> groceryListMgr = null;
    public static Context mainContext;
    ListView listView;
    GroceryList editList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
        CheckBox selectAll = (CheckBox) findViewById(R.id.selectall);
        setSupportActionBar(toolbar);
        groceryListMgr = getArrayVal(getApplicationContext());
        DatabaseStore.dbStore = getDatabase(getApplicationContext());
        //initialize, unselect all selected lists
        if (groceryListMgr != null){
            for (GroceryList groceryList : groceryListMgr){
                groceryList.setSelected(false);
            }
        }
        displayListView();
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (groceryListMgr != null){
                    for (GroceryList groceryList : groceryListMgr){
                        groceryList.setSelected(isChecked);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add List");
            final EditText input = new EditText(this);
            builder.setView(input);
            input.setId(R.id.my_edit_text);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GroceryList list = new GroceryList(input.getText().toString(), false);
                    groceryListMgr.add(list);
                    listView.setAdapter(dataAdapter);
                    MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }

        if (id == R.id.action_edit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            boolean toEdit = false;


            for (GroceryList list : groceryListMgr){
                if (list.isSelected()){
                    if (toEdit){
                        Context context = getApplicationContext();
                        CharSequence text = "Only one list can be selected for editing";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return true;
                    }
                    else {
                        toEdit = true;
                        editList = list;
                    }
                }
            }
            if (!toEdit) {
                Context context = getApplicationContext();
                CharSequence text = "Please select one list for editing";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                builder.setTitle("Enter new name for list");
                final EditText input = new EditText(this);
                builder.setView(input);
                input.setId(R.id.my_edit_text);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editList.setName(input.getText().toString());
                        listView.setAdapter(dataAdapter);
                        MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
        }

        if (id == R.id.action_deleteAll) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete All Lists");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    groceryListMgr.clear();
                    listView.setAdapter(dataAdapter);
                    MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Selected Lists");
            boolean toDelete = false;
            for(GroceryList list : groceryListMgr){
                if (list.isSelected()){
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
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = groceryListMgr.size() - 1; i >= 0; i--) {
                            if (groceryListMgr.get(i).isSelected() == true) {
                                groceryListMgr.remove(i);

                            }
                        }
                        listView.setAdapter(dataAdapter);
                        MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());
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

    public static void storeArrayVal(ArrayList<GroceryList> inArrayList, Context context){
        SharedPreferences data;
        SharedPreferences.Editor editor;
        data = context.getSharedPreferences("groceryLists",Context.MODE_PRIVATE);
        editor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(inArrayList);
        editor.putString("listData", json);
        editor.commit();
    }

    public static ArrayList<GroceryList> getArrayVal( Context context){
        SharedPreferences data;
        ArrayList<GroceryList> newArrayList;
        data = context.getSharedPreferences("groceryLists",Context.MODE_PRIVATE);
        if (data.contains("listData")) {
            String json = data.getString("listData", null);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<GroceryList>>() {}.getType();
            newArrayList = gson.fromJson(json, type);
        } else{
            return null;
        }
        return newArrayList;
    }

    public static void storeDatabase(DatabaseStore dbStore, Context context){
        SharedPreferences data;
        SharedPreferences.Editor editor;
        data = context.getSharedPreferences("database",Context.MODE_PRIVATE);
        editor = data.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dbStore);
        editor.putString("databaseData", json);
        editor.commit();
    }

    public static DatabaseStore getDatabase( Context context){
        SharedPreferences data;
        DatabaseStore dbStore;
        data = context.getSharedPreferences("database",Context.MODE_PRIVATE);
        if (data.contains("databaseData")) {
            String json = data.getString("databaseData", null);
            Gson gson = new Gson();
            Type type = new TypeToken<DatabaseStore>() {}.getType();
            dbStore = gson.fromJson(json, type);
            return dbStore;
        } else{
            return DatabaseStore.getInstance(context);
        }

    }

    private void displayListView(){

        if (groceryListMgr == null) {
            groceryListMgr = new ArrayList<GroceryList>();
            /*
            GroceryList danielList = new GroceryList("Daniel List", false);
            groceryListMgr.add(danielList);
            GroceryList gopalList = new GroceryList("Gopal List", false);
            groceryListMgr.add(gopalList);
            GroceryList lauraList = new GroceryList("Laura List", false);
            groceryListMgr.add(lauraList);
            GroceryList thienList = new GroceryList("Thien List", false);
            groceryListMgr.add(thienList);
            */
        }
        dataAdapter = new MyCustomAdapter(this,
                R.layout.list_rowinfo, groceryListMgr);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<GroceryList> {

        private ArrayList<GroceryList> lgroceryListMgr;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<GroceryList> groceryListMgr) {
            super(context, textViewResourceId, groceryListMgr);

        }

        private class ViewHolder {
            TextView name;
            CheckBox check;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            Log.v("ConvertView", String.valueOf(position));
            this.lgroceryListMgr = new ArrayList<GroceryList>();
            this.lgroceryListMgr.addAll(groceryListMgr);


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_rowinfo, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.check = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.check.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        // list = (List) cb.getTag();

                        GroceryList list = lgroceryListMgr.get(position);

                        list.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            GroceryList list = groceryListMgr.get(position);

            holder.name.setText(list.getName());
            //holder.check.setText(list.getName());
            holder.check.setChecked(list.isSelected());
            holder.check.setTag(list);
           // MainActivity.storeArrayVal(groceryListMgr, getApplicationContext());
            return convertView;

        }

    }

}
