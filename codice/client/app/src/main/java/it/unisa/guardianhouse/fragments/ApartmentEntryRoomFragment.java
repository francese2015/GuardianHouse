package it.unisa.guardianhouse.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryRoomFragment extends Fragment {

    ButtonRectangle btnLinkToReview;

    public ApartmentEntryRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_4_room, container, false);

        btnLinkToReview = (ButtonRectangle) view.findViewById(R.id.button_to_review);



        //link al Fragment Recensione
        btnLinkToReview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ApartmentEntryReviewFragment apartmentEntryReviewFragment = new ApartmentEntryReviewFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryReviewFragment, "Recensione");
            }
        });


        return view;

    }
}
