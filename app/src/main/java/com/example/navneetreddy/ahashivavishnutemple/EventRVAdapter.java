package com.example.navneetreddy.ahashivavishnutemple;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;


/**
 * Created by NavneetReddy on 8/5/15.
 */
public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.EventViewHolder> {

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

    HashMap<String, Date> events;

    public EventRVAdapter() {//HashMap<String, Date> events) {
//        this.events = events;
    }

    @Override
    public int getItemCount() {
        if (events != null) {
            return events.size();
        }

        return 5;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_event, viewGroup, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventViewHolder evh, int i) {
        evh.eventTitle.setText("Title for the Event");
        evh.eventDateTime.setText("8:30 pm 4/2/2015");
    }
}