package com.example.navneetreddy.ahashivavishnutemple;

import android.app.FragmentManager;

import java.util.ArrayList;


/**
 * Created by NavneetReddy on 8/5/15.
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private static FragmentManager fragmentManager;
    private static ArrayList<Event> events;


    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    private Singleton() {}

    public static void setFragmentManager(FragmentManager fragmentManager) {
        Singleton.fragmentManager = fragmentManager;
    }

    public static FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public static void setEvents(ArrayList<Event> events) {
        Singleton.events = events;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }
}