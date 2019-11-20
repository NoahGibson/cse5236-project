package com.example.cse5236app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryRequest extends AsyncTask<String, Integer, String> {

    Context context;
    TextView t1;
    EditText e1;

    DictionaryRequest(Context context, TextView t1) {
        this.context = context;
        this.t1 = t1;
    }

    @Override
    protected String doInBackground(String... params) {

        String word = params[0];
        String myUrl;
        final String app_id = "d06e0e07";
        final String app_key = "2a1fa1e4702a4ab28f11be906c5c8eec";

        /**
         * creates the url
         */
        final String language = "en-gb";
        final String word_id = word.toLowerCase();
        myUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id;

        try {
            URL url = new URL(myUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            // read the output from the server

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String def;
        super.onPostExecute(result);

        try {
            JSONObject js = new JSONObject(result);
            JSONArray json_result = js.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONArray("lexicalEntries")
                    .getJSONObject(0)
                    .getJSONArray("entries")
                    .getJSONObject(0)
                    .getJSONArray("senses")
                    .getJSONObject(0)
                    .getJSONArray("definitions");

            def = json_result.getString(0);
            t1.setText(def);
            Toast toast = Toast.makeText(context,
                    "Definition found!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(context,
                    "Definition not found", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

}


