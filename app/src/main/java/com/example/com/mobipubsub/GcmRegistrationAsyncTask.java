package com.example.com.mobipubsub;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mobipubsub.backend.registration.Registration;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vlad on 25.05.2015.
 */
class GcmRegistrationAsyncTask extends AsyncTask<Void, Void, String> {
    private static Registration regService = null;
    private GoogleCloudMessaging gcm;
    private Context context;
    private Set<String> catStringPref = null;

    private static final String SENDER_ID = "23378659771";

    public GcmRegistrationAsyncTask(Context context, Set<String> catStringPref) {
        this.context = context;
        this.catStringPref = catStringPref;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (regService == null) {

            Registration.Builder builder = new Registration.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            )
            .setRootUrl("https://bold-column-95517.appspot.com/_ah/api/");

            regService = builder.build();
        }

        String msg = "";
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(context);
            }
            String regId = gcm.register(SENDER_ID);
            msg = "Device registered, registration ID=" + regId;

            List <String> catStringPrefList = new ArrayList<String>(catStringPref);
            JSONArray mJSONArray = new JSONArray(catStringPrefList);
            regService.register(regId, mJSONArray.toString()).execute();
        } catch (IOException ex) {
            ex.printStackTrace();
            msg = "Error: " + ex.getMessage();
        }
        return msg;
    }

    @Override
    protected void onPostExecute(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Logger.getLogger("REGISTRATION").log(Level.INFO, msg);
    }
}
