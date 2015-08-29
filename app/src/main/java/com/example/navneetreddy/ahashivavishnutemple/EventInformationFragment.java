package com.example.navneetreddy.ahashivavishnutemple;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.TimeZone;


/**
 * Fragment containing the information about a given event.
 *
 * @author Navneet Reddy
 */
public class EventInformationFragment extends Fragment {

    /* Public fields required for Google Calendar. */
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    /* The indices for the projection array above. */
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    private static final int REQUEST_CODE_EMAIL = 1;

    private String[] calendarInfo;

    private String userEmail;

    private Event event;

    private OutlineTextView eventTitleText;
    private TextView dateText;
    private TextView timeText;
    private TextView contactNameText;
    private TextView contactPhoneText;
    private TextView contactEmailText;

    private ImageButton callButton;
    private ImageButton emailButton;

    private Button viewEventDetailsButton;
    private Button addToGoogleCalendarButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields(view);
        setFields();
        setClickListeners();
    }

    /**
     * Initializes all the views in the fragment.
     *
     * @param view Root view of the fragment.
     */
    private void initializeFields(View view) {
        event = Singleton.getEventToDisplay();

        eventTitleText = (OutlineTextView) view.findViewById(R.id.eventTitle);
        dateText = (TextView) view.findViewById(R.id.eventDate);
        timeText = (TextView) view.findViewById(R.id.eventTime);
        contactNameText = (TextView) view.findViewById(R.id.eventContactName);
        contactPhoneText = (TextView) view.findViewById(R.id.eventContactPhone);
        contactEmailText = (TextView) view.findViewById(R.id.eventContactEmail);

        callButton = (ImageButton) view.findViewById(R.id.eventContactCallButton);
        emailButton = (ImageButton) view.findViewById(R.id.eventContactEmailButton);

        viewEventDetailsButton = (Button) view.findViewById(R.id.viewEventDetailsButton);
        addToGoogleCalendarButton = (Button) view.findViewById(R.id.addToGoogleCalendarButton);
    }

    /**
     * Sets the text for all the text views and loads images into the image buttons.
     */
    private void setFields() {
        eventTitleText.setText(event.getName());
        dateText.setText(event.getDate());
        contactNameText.setText(event.getContactName());
        contactPhoneText.setText(event.getContactPhone());
        contactEmailText.setText(event.getContactEmail());

        if (event.isAllDay()) {
            timeText.setText("All Day");
        } else {
            timeText.setText(event.getStartTime() + " - " + event.getEndTime());
        }

        Picasso.with(getActivity())
                .load(R.drawable.ic_call_black_24dp)
                .fit()
                .centerInside()
                .into(callButton);

        Picasso.with(getActivity())
                .load(R.drawable.ic_email_black_24dp)
                .fit()
                .centerInside()
                .into(emailButton);

        /* Gets the email address of the user. */
        try {
            Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                    new String[] {GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE},
                    false, null, null, null, null);
            startActivityForResult(intent, REQUEST_CODE_EMAIL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == Activity.RESULT_OK) {
            userEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            calendarInfo = getCalendar();
        }
    }

    /**
     * On click listeners for all the buttons on the fragment.
     */
    private void setClickListeners() {
        contactPhoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + event.getContactPhone()));
                startActivity(intent);
            }
        });

        contactEmailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSubject = ("Re: " + event.getName() + " - (Sent from AHA Android App)");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{event.getContactEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + event.getContactPhone()));
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSubject = ("Re: " + event.getName() + " - (Sent from AHA Android App)");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{event.getContactEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        viewEventDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfUrl = event.getPdfLink();
                String pdfFileName = pdfUrl.substring(pdfUrl.lastIndexOf('/') + 1);

                new PDFDownloadAsyncTask().execute(pdfUrl, pdfFileName);
            }
        });

        addToGoogleCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToGoogleCalendar();
            }
        });
    }

    /**
     * Retrieves the calendar information of the user's Google Calendar.
     *
     * @return String array containing the calendar information.
     */
    private String[] getCalendar() {
        Cursor cur;
        ContentResolver cr = getActivity().getContentResolver();

        Uri uri = CalendarContract.Calendars.CONTENT_URI;

        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";

        String[] selectionArgs = new String[] {userEmail , "com.google", userEmail};

        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        long calID = 0;
        String displayName = null;
        String accountName = null;
        String ownerName = null;

        while (cur.moveToNext()) {
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
        }

        cur.close();

        return new String[] {String.valueOf(calID), displayName, accountName, ownerName};
    }

    /**
     * Saves the event information to the user's Google Calendar.
     */
    private void saveToGoogleCalendar() {
        Date startDate = event.getStartDate();
        Date endDate = event.getEndDate();
        String title = event.getName();
        String location = "AHA Shiva Vishnu Temple\n" +
                "2138 South Fish Hatchery Road, Fitchburg, WI 53575";

        long calID = Long.parseLong(calendarInfo[PROJECTION_ID_INDEX]);

        ContentResolver cr = getActivity().getContentResolver();
        ContentValues values = new ContentValues();

        values.put(CalendarContract.Events.DTSTART, startDate.getTime());
        values.put(CalendarContract.Events.DTEND, endDate.getTime());
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.EVENT_LOCATION, location);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getTimeZone("America/Chicago").getID());

        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

        long eventID = Long.parseLong(uri.getLastPathSegment());

        if(eventID != -1) {
            new AlertDialog.Builder(getActivity())
                    .setCancelable(true)
                    .setTitle("Google Calendar")
                    .setMessage("This event had been successfully saved to your Google Calendar.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(getActivity())
                    .setCancelable(true)
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .setTitle("Google Calendar Error")
                    .setMessage("An error occurred while saving this event to your Google Calendar.")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveToGoogleCalendar();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }
}