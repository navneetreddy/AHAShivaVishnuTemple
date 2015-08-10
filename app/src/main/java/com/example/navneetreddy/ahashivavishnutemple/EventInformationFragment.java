package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventInformationFragment extends Fragment {

    private Event event;

    private OutlineTextView eventTitleText;
    private TextView dateText;
    private TextView timeText;
    private TextView contactNameText;
    private TextView contactPhoneText;
    private TextView contactEmailText;

    private Button addToGoogleCalendarButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields(view);
        setFields();
        setClickListeners();
    }

    private void initializeFields(View view) {
        event = Singleton.getEventToDisplay();

        eventTitleText = (OutlineTextView) view.findViewById(R.id.eventTitle);
        dateText = (TextView) view.findViewById(R.id.eventDate);
        timeText = (TextView) view.findViewById(R.id.eventTime);
        contactNameText = (TextView) view.findViewById(R.id.eventContactName);
        contactPhoneText = (TextView) view.findViewById(R.id.eventContactPhone);
        contactEmailText = (TextView) view.findViewById(R.id.eventContactEmail);

        addToGoogleCalendarButton = (Button) view.findViewById(R.id.addToGoogleCalendarButton);
    }

    private void setFields() {
        eventTitleText.setText(event.getName());
        dateText.setText(event.getDate());
        timeText.setText(event.getTime());
        contactNameText.setText(event.getContactName());
        contactPhoneText.setText(event.getContactPhone());
        contactEmailText.setText(event.getContactEmail());
    }

    private void setClickListeners() {
        contactPhoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        contactEmailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addToGoogleCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}