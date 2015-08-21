package com.example.navneetreddy.ahashivavishnutemple;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Fragment containing information about the developer of this app - Navneet Reddy.
 *
 * @author Navneet Reddy
 */
public class AboutDeveloperFragment extends Fragment {

    private ImageButton imageThumb;
    private Button emailButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_developer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageThumb = (ImageButton) view.findViewById(R.id.navneetPicture);
        emailButton = (Button) view.findViewById(R.id.navneetEmailButton);

        TextView description = (TextView) view.findViewById(R.id.navneetDescription);

        String descriptionText = "";

        description.setText(descriptionText);

        imageThumbClickListener();
        emailButtonClickListener();
    }

    /**
     * Zooms the image to full screen when the image thumbnail is clicked.
     */
    private void imageThumbClickListener() {
        imageThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageThumb.setEnabled(false);

                // TODO - image zoom.
            }
        });
    }

    /**
     * Starts an intent to email the developer.
     */
    private void emailButtonClickListener() {
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButton.setEnabled(false);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"navneet@tds.net"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message from AHA Android App");

                startActivity(Intent.createChooser(emailIntent, "Choose Mail Application"));
            }
        });
    }

    @Override
    public void onResume() {
        super.onCreate(null);
        imageThumb.setEnabled(true);
        emailButton.setEnabled(true);
    }
}