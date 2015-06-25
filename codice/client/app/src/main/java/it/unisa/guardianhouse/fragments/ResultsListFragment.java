package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ApartmentListAdapter;
import it.unisa.guardianhouse.models.Apartment;


public class ResultsListFragment extends Fragment {

    private ListView listView;
    ApartmentListAdapter adapter;
    ArrayList<Apartment> apartmentList;

    public ResultsListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_list, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        apartmentList = bundle.getParcelableArrayList("aptData");

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(getActivity(), apartmentList);
        listView.setAdapter(adapter);
        listView.setEmptyView(view.findViewById(R.id.empty_list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Object obj = adapter.getItemAtPosition(position);
                Apartment apt = (Apartment)obj;
                Bundle b = new Bundle();
                b.putString("aptId", apt.getId());
                b.putDouble("itemLatitude", apt.getLatitude());
                b.putDouble("itemLongitude", apt.getLongitude());
                b.putDouble("distance", apt.getDistanceFromLocation());
                ApartmentFragment aptFragment = new ApartmentFragment();
                aptFragment.setArguments(b);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(aptFragment, "Scheda appartamento");
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
