package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


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

        // TODO - remove the past events from the list here.
        // Store events as a date to Event class.
        // Singleton.setEvents(removePastDates(Singleton.getEvents()));

        /* Set up the recycler view */
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.upcomingEventsRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        EventRVAdapter adapter = new EventRVAdapter();
        rv.setAdapter(adapter);
    }

//    private ArrayList<Event> removePastDates(ArrayList<Event> events) {
//        // TODO
//        return null;
//    }
}