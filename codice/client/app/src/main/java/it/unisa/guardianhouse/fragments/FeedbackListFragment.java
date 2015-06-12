package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.FeedbackListAdapter;
import it.unisa.guardianhouse.models.Feedback;


public class FeedbackListFragment extends Fragment {

    private ListView listView;
    FeedbackListAdapter adapter;
    ArrayList<Feedback> feedbacksList;


    public FeedbackListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

       // Bundle bundle = getArguments();
        //feedbacksList = bundle.getStringArrayList();
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
