package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryReviewFragment extends Fragment {

    ButtonRectangle btnToSave;
    EditText inputReviewTitle;
    EditText inputReview;
    RatingBar inputRate;

    String title;
    String review;
    float rate;

    Bundle bundle ;


    public ApartmentEntryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_5_review, container, false);

        btnToSave = (ButtonRectangle) view.findViewById(R.id.button_to_save);
        inputReviewTitle = (EditText) view.findViewById(R.id.review_title);
        inputReview = (EditText) view.findViewById(R.id.review);
        inputRate = (RatingBar) view.findViewById(R.id.reviewRatingBar);

        btnToSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                title = inputReviewTitle.getText().toString();
                review = inputReview.getText().toString();
                rate = inputRate.getRating();

                if (!title.isEmpty() && !review.isEmpty()) {

                    bundle = getArguments();
                    bundle.putString("myTitle", title);
                    bundle.putString("myReview", review);
                    bundle.putFloat("myRate", rate);

                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);

                    Toast.makeText(getActivity(),
                            "Salvataggio Completato :)", Toast.LENGTH_LONG)
                            .show();


                } else {


                    Toast.makeText(getActivity(),
                            "Inserisci Tutti i Campi!", Toast.LENGTH_LONG)
                            .show();


                }

            }

        });


        return view;
    }
}
