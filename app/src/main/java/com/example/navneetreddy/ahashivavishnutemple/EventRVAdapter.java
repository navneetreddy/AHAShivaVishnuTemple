package com.example.navneetreddy.ahashivavishnutemple;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Adapter for the recycler view to display all the upcoming events.
 *
 * @author Navneet Reddy
 */
public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.EventViewHolder> {

    /**
     * View Holder that holds information about an event.
     */
    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView eventTitle;
        TextView eventDateTime;

        EventViewHolder(View view) {
            super(view);

            cv = (CardView) view.findViewById(R.id.event_card_view);
            eventTitle = (TextView) view.findViewById(R.id.event_title);
            eventDateTime = (TextView) view.findViewById(R.id.event_date_time);
        }
    }

    ArrayList<Event> events;

    public EventRVAdapter() {
        this.events = Singleton.getEvents();
    }

    @Override
    public int getItemCount() {
        if (events != null) {
            return events.size();
        }

        return 0;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_event, viewGroup, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder evh, final int i) {
        evh.eventTitle.setText(events.get(i).getName());

        if (events.get(i).isAllDay()) {
            evh.eventDateTime.setText("All Day Event on " + events.get(i).getDate());
        } else {
            evh.eventDateTime.setText(events.get(i).getStartTime() + " " + events.get(i).getDate());
        }

        evh.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.setEventToDisplay(events.get(i));

                Singleton.getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new EventInformationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}