package com.example.com.mobipubsub;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vlad on 01.06.2015.
 */
public class SavedNews {
    public final static String SAVED_NEWS_KEY = "com.example.myapp.SAVED_NEWS";


    public static Set<String> getSavedNews(Context ctx) {
        SharedPreferences categoryPref = ctx.getSharedPreferences(SAVED_NEWS_KEY, Context.MODE_PRIVATE);
        return categoryPref.getStringSet(SAVED_NEWS_KEY, new HashSet<String>());
    }

    public static void setSavedNews(Context ctx, Set<String> set) {
        SharedPreferences categoryPref = ctx.getSharedPreferences(SAVED_NEWS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = categoryPref.edit();
        editor.putStringSet(SAVED_NEWS_KEY, set);
        editor.apply();
    }
}
