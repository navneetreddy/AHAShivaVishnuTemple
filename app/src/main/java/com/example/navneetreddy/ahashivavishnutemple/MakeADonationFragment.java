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
 * A simple {@link Fragment} subclass.
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

        expandableListView = (ExpandableListView) view.findViewById(R.id.aboutUsExpandableListView);
        expandableListAdapter = new ExpandableListAdapter(getActivity(),
                expandableListHeaderData, expandableListChildData);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setListData() {


    }
}