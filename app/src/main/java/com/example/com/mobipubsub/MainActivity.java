package com.example.com.mobipubsub;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public final static String CAT_PREF_KEY = "com.example.myapp.PREFERENCE_FILE_KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        SharedPreferences categoryPref = this.getSharedPreferences(CAT_PREF_KEY, Context.MODE_PRIVATE);
        Set<String> catStringPref = categoryPref.getStringSet(CAT_PREF_KEY, new HashSet<String>());

        new GcmRegistrationAsyncTask(this, catStringPref).execute();

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

}
