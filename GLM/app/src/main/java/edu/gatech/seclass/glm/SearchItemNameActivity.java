package edu.gatech.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchItemNameActivity extends AppCompatActivity {

    EditText searchText;
    int selglPosition = -1;
    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);
        searchText = (EditText) findViewById(R.id.searchText);
        Intent intent = getIntent();
        selglPosition = intent.getIntExtra("position", -1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        // \getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button searchButton = (Button) findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTextValue = searchText.getText().toString();

                DatabaseStore dbStore = DatabaseStore.getInstance(getApplicationContext());
                ArrayList<Item> items = dbStore.getSimilarItem(searchTextValue);

                // Case where there are no matches
                if(items.size() == 0) {
                    Context context = getApplicationContext();
                    CharSequence text = "Item Name does not exist. Please add a new item.";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = new Intent(SearchItemNameActivity.this, AddItemActivity.class);
                    intent.putExtra("selSearchName", searchTextValue);
                    intent.putExtra("position", selglPosition);
                    startActivity(intent);
                }

                // With matches, handle normally
                else {
                    // Define intent, pass info and start ViewItemNameActivity
                    Intent intent = new Intent(SearchItemNameActivity.this, ViewItemNameActivity.class);
                    intent.putExtra("selSearchName", searchTextValue);
                    intent.putExtra("position", selglPosition);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;

        }
        */
        return true;
    }
}

