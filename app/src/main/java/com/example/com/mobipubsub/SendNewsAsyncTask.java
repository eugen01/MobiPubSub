package com.example.com.mobipubsub;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mobipubsub.backend.registration.Registration;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
/**
 * Created by vlad on 25.05.2015.
 */
class SendNewsAsync extends AsyncTask<Void, Void, String> {
    private static Registration myApiService = null;

    private Context context;

    private String content;
    private String categories;


    public SendNewsAsync(Context ctx, String content, String categories) {
        this.context = ctx;
        this.content = content;
        this.categories = categories;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            Registration.Builder builder = new Registration.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            )
            .setRootUrl("https://bold-column-95517.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        String msg = "";
        try {
            Log.d("test", content);
            Log.d("test", categories);
            myApiService.postNews(content, categories).execute();
            msg = "News posted";

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return msg;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}