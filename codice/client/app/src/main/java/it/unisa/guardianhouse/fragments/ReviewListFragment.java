package it.unisa.guardianhouse.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ReviewListAdapter;
import it.unisa.guardianhouse.models.Review;

/**
 * Created by Luigi on 26/05/2015.
 */
public class ReviewListFragment extends Fragment {

    private ListView listView;
    ReviewListAdapter adapter;
    ArrayList<Review> reviewArrayList;


    public ReviewListFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_list, container, false);

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ReviewListAdapter(getActivity(), reviewArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Object obj = adapter.getItemAtPosition(position);
                Review rew = (Review)obj;
                Bundle b = new Bundle();
                b.putString("rewId", rew.getRewId());
                ReviewFragment rewFragment = new ReviewFragment();
                rewFragment.setArguments(b);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(rewFragment, "Scheda Recensione");
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


