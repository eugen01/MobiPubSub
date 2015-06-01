package com.example.com.mobipubsub;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> list = new ArrayList<String>();

    public ArrayAdapter<String> adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        new GcmRegistrationAsyncTask(this).execute();

    }

    @Override
    public void onResume() {
        super.onResume();

        list = new ArrayList<String>(SavedNews.getSavedNews(this));

        final ListView prefList = (ListView)this.findViewById(R.id.news_list);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);

        prefList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent profile = new Intent(this, Profile.class);
            if (profile.resolveActivity(getPackageManager()) != null) {
                startActivity(profile);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void postNews(View view) {
        TextView msg_content = (TextView)this.findViewById(R.id.msg_content);
        TextView categories = (TextView)this.findViewById(R.id.categories);
        new SendNewsAsync(this, msg_content.getText().toString(), categories.getText().toString()).execute();
    }

    public void refresh(View view) {
        list.clear();
        list.addAll(SavedNews.getSavedNews(this));
        adapter.notifyDataSetChanged();
    }

}
