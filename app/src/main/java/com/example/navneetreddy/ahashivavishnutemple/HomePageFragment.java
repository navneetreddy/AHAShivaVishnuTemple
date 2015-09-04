package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Home page of the app where the users are directed to after the splash screen.
 *
 * @author Navneet Reddy
 */
public class HomePageFragment extends Fragment {

    private Button aboutUsButton;
    private Button contactUsButton;
    private Button upcomingEventsButton;
    private Button makeADonationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields(view);
        setButtonOnClickListeners();
    }

    @Override
    public void onResume() {
        super.onCreate(null);

        aboutUsButton.setEnabled(true);
        contactUsButton.setEnabled(true);
        upcomingEventsButton.setEnabled(true);
        makeADonationButton.setEnabled(true);
    }

    /**
     * Initializes all the views in the fragment.
     *
     * @param view Root view of the fragment.
     */
    private void initializeFields(View view) {
        aboutUsButton = (Button) view.findViewById(R.id.aboutUsButton);
        contactUsButton = (Button) view.findViewById(R.id.contactUsButton);
        upcomingEventsButton = (Button) view.findViewById(R.id.upcomingEventsButton);
        makeADonationButton = (Button) view.findViewById(R.id.makeADonationButton);
    }

    /**
     * On click listeners for all the buttons on the fragment.
     */
    private void setButtonOnClickListeners() {
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(v, new AboutUsFragment());
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(v, new ContactUsFragment());
            }
        });

        upcomingEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(v, new UpcomingEventsFragment());
            }
        });

        makeADonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(v, new MakeADonationFragment());
            }
        });
    }

    /**
     * Inflates and displays the given fragment.
     *
     * @param v Button that the user had clicked.
     * @param fragment Fragment that will to be displayed.
     */
    private void goToFragment(View v, Fragment fragment) {
        v.setEnabled(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}