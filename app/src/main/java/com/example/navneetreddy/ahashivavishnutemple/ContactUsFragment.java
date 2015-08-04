package com.example.navneetreddy.ahashivavishnutemple;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {

    private Button directionsButton;
    private Button callButton;
    private Button emailButton;
    private Button websiteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

    private void setOnClickListeners() {
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directionsButton.setEnabled(false);

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=2138+South+Fish+Hatchery+Road," +
                        "+Fitchburg,+WI+53575");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton.setEnabled(false);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:6082348634"));
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButton.setEnabled(false);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact.aha@aha-svtemple.org"});
                startActivity(intent);
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