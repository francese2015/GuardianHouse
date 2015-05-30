package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;

import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryReviewFragment extends Fragment {

    ButtonRectangle btnToSave;


    Bundle bundle ;


    public ApartmentEntryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_3_review, container, false);

        btnToSave = (ButtonRectangle) view.findViewById(R.id.button_to_save);

        // inputReviewTitle = (EditText) view.findViewById(R.id.review_title);


        btnToSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                

            }

        });


        return view;
    }
}
