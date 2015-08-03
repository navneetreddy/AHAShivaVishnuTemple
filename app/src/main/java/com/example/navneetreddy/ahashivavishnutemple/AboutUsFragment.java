package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    TextView aha;
    TextView vision;
    TextView mission;
    TextView volunteer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        aha = (TextView) view.findViewById(R.id.aboutUsAHA);
        vision = (TextView) view.findViewById(R.id.aboutUsVision);
        mission = (TextView) view.findViewById(R.id.aboutUsMission);
        volunteer = (TextView) view.findViewById(R.id.aboutUsVolunteering);

        fillTextViews();
    }

    private void fillTextViews() {
        aha.setText("American Hindu Association (AHA) is a non-profit Hindu cultural and " +
                "heritage organization founded in July of 1997 to serve the greater Madison area " +
                "community under Section 501 (C) (3) of United States Internal Revenue Service " +
                "tax code. The devotees of AHA, who are currently residents of greater Madison " +
                "area, migrated from various places around the world including Canada, Fiji " +
                "Islands, Guyana, India, Malaysia, Nepal, Sri Lanka, United Kingdom, and other " +
                "parts of the United States.\n" + "AHA is funded primarily from community and " +
                "devotee donations.");

        vision.setText("A well respected and supported organization that provides leadership and " +
                "opportunities for members of the Hindu community of greater Madison in " +
                "preserving, espousing and enhancing our cultural, religious, educational, " +
                "societal, and other heritage related needs.");

        mission.setText("1. Providing services to devotees of all ages in order to promote and " +
                "enhance the awareness of principles and practices of Hinduism through " +
                "activities such as regular prayers, recitals, and public discourses by " +
                "scholars and invited guests.\n" +
                "2. Sponsoring and organizing religious festivals and cultural events for all " +
                "sections of the Indian community to ensure that everyone has an opportunity to " +
                "celebrate such occasions as done in their respective home countries.\n" +
                "3. Promoting and providing yoga, meditation and other educational activities to " +
                "support personal and spiritual growth of devotees.\n" +
                "4. Developing, supporting and implementing appropriate devotee activities in " +
                "support of local, regional, and national community based services and programs.");

        volunteer.setText("AHA is a fully volunteer run organization. We welcome members of our " +
                "community who are interested in volunteering for the various programs and " +
                "services provided by AHA. If you are interested please fill in a " +
                "<a href=\\\"http://aha-svtemple.org/about-us/volunteer-registration/\\\">" +
                "Volunteer Registration Form</a>.");
    }
}