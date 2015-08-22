package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * Fragment to display a list of upcoming events in a recycler view.
 *
 * @author Navneet Reddy
 */
public class UpcomingEventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming_events, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GetEventsAsyncTask task = new GetEventsAsyncTask();

        try {
            Singleton.setEvents(task.execute().get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setEventDates();

        /* Set up the recycler view */
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.upcomingEventsRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        EventRVAdapter adapter = new EventRVAdapter();
        rv.setAdapter(adapter);
    }

    /**
     * Sets the start and end dates as a date objects for each event.
     * If the time for the event is before the current time, remove the event from the list.
     */
    private void setEventDates() {
        Calendar startDateCalendar = Calendar.getInstance();
        Calendar endDateCalendar = Calendar.getInstance();

        ArrayList<Event> events = Singleton.getEvents();

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);

            String[] dateArray = event.getDate().split("/");
            String[] startTimeArray = event.getStartTime().split("(:)|(\\s)");
            String[] endTimeArray = event.getEndTime().split("(:)|(\\s)");

            startDateCalendar.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
            endDateCalendar.setTimeZone(TimeZone.getTimeZone("America/Chicago"));

            startDateCalendar.set(
                    Integer.parseInt(dateArray[2]),                     // Year
                    Integer.parseInt(dateArray[0]) - 1,                 // Month
                    Integer.parseInt(dateArray[1]),                     // Day
                    Integer.parseInt(startTimeArray[0]),                // Hour
                    Integer.parseInt(startTimeArray[1]));               // Minute

            endDateCalendar.set(
                    Integer.parseInt(dateArray[2]),                     // Year
                    Integer.parseInt(dateArray[0]) - 1,                 // Month
                    Integer.parseInt(dateArray[1]),                     // Day
                    Integer.parseInt(endTimeArray[0]),                  // Hour
                    Integer.parseInt(endTimeArray[1]));                 // Minute

            switch (startTimeArray[2]) {
                case "am": startDateCalendar.set(Calendar.AM_PM, Calendar.AM); break;
                case "pm": startDateCalendar.set(Calendar.AM_PM, Calendar.PM); break;
                default: break;
            }

            switch (endTimeArray[2]) {
                case "am": endDateCalendar.set(Calendar.AM_PM, Calendar.AM); break;
                case "pm": endDateCalendar.set(Calendar.AM_PM, Calendar.PM); break;
                default: break;
            }

            /* Remove the event if it has already happened,
            otherwise set the start and end dates. */
            if (startDateCalendar.before(Calendar.getInstance())) {
                Singleton.getEvents().remove(event);
            } else {
                event.setStartDate(startDateCalendar.getTime());
                event.setEndDate(endDateCalendar.getTime());
            }
        }
    }
}