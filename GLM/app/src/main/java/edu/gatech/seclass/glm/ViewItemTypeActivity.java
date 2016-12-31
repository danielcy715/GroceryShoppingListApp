package edu.gatech.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewItemTypeActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter = null;
    ArrayList<String> itemTypes;
    ListView listView;
    int selglPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_type);
        listView = (ListView) findViewById(R.id.vtlistView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        selglPosition = intent.getIntExtra("position", -1);
        displayitemTypes();

    }

    private void displayitemTypes() {
        itemTypes = new ArrayList<String>();

        DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
        String[] itemTypes = dbStore.getItemTypes1();
//        itemTypes.addAll(dbStore.getItemTypes1());


        final ArrayList<String> itemTypesArr = new ArrayList<String>(Arrays.asList(itemTypes));
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_view, R.id.item,itemTypesArr);

        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, jump to the list actiivity
                Intent intent = new Intent(ViewItemTypeActivity.this, ViewItemNameActivity.class);
                intent.putExtra("position", selglPosition);
                String selType = itemTypesArr.get(position);
                intent.putExtra("selItemType", selType);
                startActivity(intent);

            }
        });

    }


    private class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               int itemType, ArrayList<String> itemTypes) {
            super(context, textViewResourceId, itemType, itemTypes);

        }

    }

}
