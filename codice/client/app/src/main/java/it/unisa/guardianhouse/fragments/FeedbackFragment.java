package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {


    ImageView imgView;
    TextView feedUsername;
    RatingBar feedRating;
    TextView feedText;



    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_feedback, container, false);

        feedUsername = (TextView) view.findViewById(R.id.feedUsername);

        feedRating = (RatingBar) view.findViewById(R.id.feedRatingBar);

        feedText = (TextView) view.findViewById(R.id.feedbackText);

        imgView = (ImageView) view.findViewById(R.id.feedProfileImage);


        return view;
    }


}
