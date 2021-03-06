package com.navneetreddy.ahashivavishnutemple;

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
 * Fragment containing information about how to donate to the temple.
 *
 * @author Navneet Reddy
 */
public class MakeADonationFragment extends Fragment {

    List<String> expandableListHeaderData;
    HashMap<String, List<String>> expandableListChildData;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_make_a_donation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListHeaderData = new ArrayList<>();
        expandableListChildData = new HashMap<>();

        setListData();

        expandableListView = (ExpandableListView) view.findViewById(R.id.donationExpandableListView);
        expandableListAdapter = new ExpandableListAdapter(getActivity(),
                expandableListHeaderData, expandableListChildData);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public void onResume() {
        super.onCreate(null);

        /* Collapse all the expandable list groups. */
        for (int i = 0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.collapseGroup(i);
    }

    /**
     * Sets the data that will be shown in the expanded list view.
     */
    private void setListData() {
        List<String> ahaFunds = new ArrayList<>();
        List<String> sponsorLevels = new ArrayList<>();
        List<String> donateMonthlyTransfer = new ArrayList<>();
        List<String> donateCheck = new ArrayList<>();

        ahaFunds.add("Building Fund");
        ahaFunds.add("Education Fund");
        ahaFunds.add("General Fund (Covers Operational Expenses)");
        ahaFunds.add("Food, Disaster & Humanitarian Aid Fund");

        sponsorLevels.add(String.format("%-23s%s", "$10,000+",    "Diamond Sponsor"));
        sponsorLevels.add(String.format("%-24s%s", "$5,000+",     "Platinum Sponsor"));
        sponsorLevels.add(String.format("%-24s%s", "$2,500+",     "Gold Sponsor"));
        sponsorLevels.add(String.format("%-24s%s", "$1,000+",     "Silver Sponsor"));
        sponsorLevels.add(String.format("%-25s%s", "$500+",       "Bronze Sponsor"));

        donateMonthlyTransfer.add("Please complete a Pledge/Donation form with appropriate bank " +
                "account details and mail it to:\n\n" +
                "The Treasurer\n" +
                "American Hindu Association\n" +
                "PO Box 628243\n" +
                "Middleton, WI 53562");

        donateCheck.add("Please complete a Pledge/Donation form and mail it, along with your " +
                "donation check (made payable to American Hindu Association) to:\n\n" +
                "The Treasurer\n" +
                "American Hindu Association\n" +
                "PO Box 628243\n" +
                "Middleton, WI 53562");

        expandableListHeaderData.add("AHA Funds Needing Sponsorship");
        expandableListHeaderData.add("Sponsor Recognition Levels");
        expandableListHeaderData.add("Donate via Monthly Automatic Funds Transfer");
        expandableListHeaderData.add("Donate by Check");
        expandableListHeaderData.add("Donate by Credit Card");

        expandableListChildData.put(expandableListHeaderData.get(0), ahaFunds);
        expandableListChildData.put(expandableListHeaderData.get(1), sponsorLevels);
        expandableListChildData.put(expandableListHeaderData.get(2), donateMonthlyTransfer);
        expandableListChildData.put(expandableListHeaderData.get(3), donateCheck);
        expandableListChildData.put(expandableListHeaderData.get(4), null);
    }
}