package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Fragment about AHA and the temple.
 *
 * @author Navneet Reddy
 */
public class AboutUsFragment extends Fragment {

    private List<String> expandableListHeaderData;
    private HashMap<String, List<String>> expandableListChildData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListHeaderData = new ArrayList<>();
        expandableListChildData = new HashMap<>();

        setListData();

        ExpandableListView expandableListView =
                (ExpandableListView) view.findViewById(R.id.aboutUsExpandableListView);
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getActivity(),
                expandableListHeaderData, expandableListChildData);
        expandableListView.setAdapter(expandableListAdapter);
    }

    /**
     * Sets the data that will be shown in the expanded list view.
     */
    private void setListData() {
        List<String> aha = new ArrayList<>();
        List<String> vision = new ArrayList<>();
        List<String> mission = new ArrayList<>();
        List<String> volunteer = new ArrayList<>();

        aha.add("American Hindu Association (AHA) is a non-profit Hindu cultural and " +
                "heritage organization founded in July of 1997 to serve the greater Madison area " +
                "community under Section 501 (C) (3) of United States Internal Revenue Service " +
                "tax code. The devotees of AHA, who are currently residents of greater Madison " +
                "area, migrated from various places around the world including Canada, Fiji " +
                "Islands, Guyana, India, Malaysia, Nepal, Sri Lanka, United Kingdom, and other " +
                "parts of the United States.\n" + "AHA is funded primarily from community and " +
                "devotee donations.");

        vision.add("A well respected and supported organization that provides leadership and " +
                "opportunities for members of the Hindu community of greater Madison in " +
                "preserving, espousing and enhancing our cultural, religious, educational, " +
                "societal, and other heritage related needs.");

        mission.add("1. Providing services to devotees of all ages in order to promote and " +
                "enhance the awareness of principles and practices of Hinduism through " +
                "activities such as regular prayers, recitals, and public discourses by " +
                "scholars and invited guests.");

        mission.add("2. Sponsoring and organizing religious festivals and cultural events for " +
                "all sections of the Indian community to ensure that everyone has an opportunity " +
                "to celebrate such occasions as done in their respective home countries.");

        mission.add("3. Promoting and providing yoga, meditation and other educational " +
                "activities to support personal and spiritual growth of devotees.");

        mission.add("4. Developing, supporting and implementing appropriate devotee activities in" +
                " support of local, regional, and national community based services and programs.");

        volunteer.add("AHA is a fully volunteer run organization. We welcome members of our " +
                "community who are interested in volunteering for the various programs and " +
                "services provided by AHA. If you are interested please fill in a " +
                "<a href=\\\"http://aha-svtemple.org/about-us/volunteer-registration/\\\">" +
                "Volunteer Registration Form</a>.");        // TODO - fix html to go to web page.

        expandableListHeaderData.add("American Hindu Association");
        expandableListHeaderData.add("Our Vision");
        expandableListHeaderData.add("Our Mission");
        expandableListHeaderData.add("Volunteering at AHA");

        expandableListChildData.put(expandableListHeaderData.get(0), aha);
        expandableListChildData.put(expandableListHeaderData.get(1), vision);
        expandableListChildData.put(expandableListHeaderData.get(2), mission);
        expandableListChildData.put(expandableListHeaderData.get(3), volunteer);
    }
}