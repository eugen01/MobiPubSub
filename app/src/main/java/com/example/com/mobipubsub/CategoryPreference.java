package com.example.com.mobipubsub;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vlad on 01.06.2015.
 */
public class CategoryPreference {
    public final static String CAT_PREF_KEY = "com.example.myapp.PREFERENCE_FILE_KEY";


    public static Set<String> getCategoryPreference(Context ctx) {
        SharedPreferences categoryPref = ctx.getSharedPreferences(CAT_PREF_KEY, Context.MODE_PRIVATE);
        return categoryPref.getStringSet(CAT_PREF_KEY, new HashSet<String>());
    }

    public static void setCategoryPreference(Context ctx, Set<String> set) {
        SharedPreferences categoryPref = ctx.getSharedPreferences(CAT_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = categoryPref.edit();
        editor.putStringSet(CAT_PREF_KEY, set);
        editor.apply();
    }

}
