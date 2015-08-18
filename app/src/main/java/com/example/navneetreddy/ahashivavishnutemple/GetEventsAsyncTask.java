package com.example.navneetreddy.ahashivavishnutemple;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by NavneetReddy on 8/9/15.
 */
public class GetEventsAsyncTask extends AsyncTask<Event, Void, ArrayList<Event>> {

    static String server_output = null;
    static String temp_output = null;

    @Override
    protected ArrayList<Event> doInBackground(Event...arg0) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            MongoQueryBuilder mqb = new MongoQueryBuilder();
            URL url = new URL(mqb.buildQueryURL());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((urlConnection.getInputStream())));

            while ((temp_output = br.readLine()) != null) {
                server_output = temp_output;
            }

            String mongoArray = ("{ artificial_basicdb_list: " + server_output + "}");

            DBObject dbObj = (DBObject) JSON.parse(mongoArray);
            BasicDBList serverEvents = (BasicDBList) dbObj.get("artificial_basicdb_list");

            for (Object serverEvent : serverEvents) {
                Event tempEvent = new Gson().fromJson(JSON.serialize(serverEvent), Event.class);
                events.add(tempEvent);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return events;
    }
}