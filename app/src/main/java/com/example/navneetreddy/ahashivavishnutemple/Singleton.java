package com.example.navneetreddy.ahashivavishnutemple;

import android.app.FragmentManager;
import android.content.Context;

import java.util.ArrayList;


/**
 * Singleton class to store variables.
 * This is a static class that can be accessed from any place in the app.
 *
 * @author Navneet Reddy
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private static Context context;
    private static FragmentManager fragmentManager;
    private static ArrayList<Event> events;
    private static Event eventToDisplay;

    /**
     * Instantiates a new instance of this class if it hasn't been instantiated already,
     * otherwise returns an instance of this class.
     *
     * @return An instance of this class.
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    /**
     * Constructor that creates a new Singleton class.
     */
    private Singleton() {}

    /**
     * Sets the application context.
     *
     * @param context The application context.
     */
    public static void setContext(Context context) {
        Singleton.context = context;
    }

    /**
     * Gets the application context.
     *
     * @return The application context.
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Sets the fragment manager.
     *
     * @param fragmentManager The fragment manager.
     */
    public static void setFragmentManager(FragmentManager fragmentManager) {
        Singleton.fragmentManager = fragmentManager;
    }

    /**
     * Gets the fragment manager.
     *
     * @return The fragment manager.
     */
    public static FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    /**
     * Sets the list of events to display on the recycler view.
     *
     * @param events List of events to display.
     */
    public static void setEvents(ArrayList<Event> events) {
        Singleton.events = events;
    }

    /**
     * Gets the list of events to display on the recycler view.
     *
     * @return List of events to display.
     */
    public static ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets the event to display on the information fragment.
     *
     * @param eventToDisplay The event to display.
     */
    public static void setEventToDisplay(Event eventToDisplay) {
        Singleton.eventToDisplay = eventToDisplay;
    }

    /**
     * Gets the event to display on the information fragment.
     *
     * @return The event to display.
     */
    public static Event getEventToDisplay() {
        return eventToDisplay;
    }
}