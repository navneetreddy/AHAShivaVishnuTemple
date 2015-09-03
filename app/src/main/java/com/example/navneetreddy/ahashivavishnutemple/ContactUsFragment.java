package com.example.navneetreddy.ahashivavishnutemple;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Fragment containing information for contacting the temple.
 *
 * @author Navneet Reddy
 */
public class ContactUsFragment extends Fragment {

    private Button directionsButton;
    private Button callButton;
    private Button emailButton;
    private Button websiteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        directionsButton = (Button) view.findViewById(R.id.directionsButton);
        callButton = (Button) view.findViewById(R.id.phoneButton);
        emailButton = (Button) view.findViewById(R.id.emailButton);
        websiteButton = (Button) view.findViewById(R.id.websiteButton);

        setOnClickListeners();
    }

    @Override
    public void onResume() {
        super.onCreate(null);

        directionsButton.setEnabled(true);
        callButton.setEnabled(true);
        emailButton.setEnabled(true);
        websiteButton.setEnabled(true);
    }

    /**
     * Handles the clicks for all the buttons in the fragment.
     */
    private void setOnClickListeners() {
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directionsButton.setEnabled(false);

//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=2138+South+Fish+Hatchery+Road," +
//                        "+Fitchburg,+WI+53575");
                Uri gmmIntentUri = Uri.parse("google.navigation:q=2138+South+Fish+Hatchery+Road," +
                        "+Fitchburg,+WI+53575");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                try {
                    startActivity(mapIntent);
                } catch (ActivityNotFoundException ae1) {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                    playStoreIntent.setData(
                            Uri.parse("market://details?id=com.google.android.apps.maps"));

                    try {
                        startActivity(playStoreIntent);
                    } catch (ActivityNotFoundException ae2) {
                        new AlertDialog.Builder(Singleton.getContext())
                                .setCancelable(true)
                                .setIcon(android.R.drawable.stat_sys_warning)
                                .setTitle("Google Maps not found!")
                                .setMessage("Please download Google Maps " +
                                        "to view directions to the temple.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                        directionsButton.setEnabled(true);
                    }
                }
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton.setEnabled(false);

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6082348634"));
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButton.setEnabled(false);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact.aha@aha-svtemple.org"});

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException ae1) {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                    playStoreIntent.setData(
                            Uri.parse("market://details?id=com.google.android.gm"));

                    try {
                        startActivity(playStoreIntent);
                    } catch (ActivityNotFoundException ae2) {
                        new AlertDialog.Builder(Singleton.getContext())
                                .setCancelable(true)
                                .setIcon(android.R.drawable.stat_sys_warning)
                                .setTitle("No Email Client Found!")
                                .setMessage("Please download an email client to send email.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                        emailButton.setEnabled(true);
                    }
                }
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                websiteButton.setEnabled(false);

                String url = "http://www.aha-svtemple.org";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}