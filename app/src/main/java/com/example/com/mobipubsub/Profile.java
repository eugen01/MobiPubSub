package com.example.com.mobipubsub;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Profile extends Activity {
    ArrayList<String> list = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Set<String> pref = CategoryPreference.getCategoryPreference(this);

        list = new ArrayList<String>(pref);

        final ListView prefList = (ListView)this.findViewById(R.id.pref_list);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);

        prefList.setAdapter(adapter);

        prefList.setLongClickable(true);

        prefList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) prefList.getItemAtPosition(position);

                list.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }

        });

    }

    public void addCategory(View view) {
        TextView category = (TextView)this.findViewById(R.id.category_text);
        list.add(category.getText().toString());
        adapter.notifyDataSetChanged();
    }

    public void saveCategories(View view) {
        CategoryPreference.setCategoryPreference(this, new HashSet<String>(list));
        new GcmRegistrationAsyncTask(this).execute();
        this.finish();
    }

}
