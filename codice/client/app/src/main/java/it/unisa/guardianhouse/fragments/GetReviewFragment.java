package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetReviewFragment extends Fragment {


    public GetReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_review, container, false);


        return view;
    }


}
