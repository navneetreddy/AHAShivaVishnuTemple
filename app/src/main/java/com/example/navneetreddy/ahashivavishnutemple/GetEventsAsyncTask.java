package com.example.navneetreddy.ahashivavishnutemple;

import android.os.AsyncTask;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

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

            if (urlConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((urlConnection.getInputStream())));

            while ((temp_output = br.readLine()) != null) {
                server_output = temp_output;
            }

            String mongoArray = ("{ artificial_basicdb_list: " + server_output + "}");
            Object o = com.mongodb.util.JSON.parse(mongoArray);

            DBObject dbObj = (DBObject) o;
            BasicDBList serverEvents = (BasicDBList) dbObj.get("artificial_basicdb_list");

            for (Object serverEvent : serverEvents) {
                DBObject userObj = (DBObject) serverEvent;

                Event tempEvent = new Event();
                tempEvent.set_id(userObj.get("_id").toString());
                tempEvent.setName(userObj.get("Event Name").toString());
                tempEvent.setDate(userObj.get("Date").toString());
                tempEvent.setTime(userObj.get("Time").toString());
                tempEvent.setContactName(userObj.get("Contact Name").toString());
                tempEvent.setContactPhone(userObj.get("Contact Phone").toString());
                tempEvent.setContactEmail(userObj.get("Contact Email").toString());
                events.add(tempEvent);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return events;
    }
}