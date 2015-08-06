package com.example.navneetreddy.ahashivavishnutemple;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PujaServicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puja_services, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Set up the recycler view */
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.upcomingEventsRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        // TODO
        EventRVAdapter adapter = new EventRVAdapter();
        rv.setAdapter(adapter);
    }
}