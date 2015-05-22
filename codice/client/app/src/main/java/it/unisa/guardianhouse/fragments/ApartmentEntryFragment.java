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
public class ApartmentEntryFragment extends Fragment {

    ButtonRectangle btnLinkToPhoto;

    public ApartmentEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1_details, container, false);

        btnLinkToPhoto = (ButtonRectangle) view.findViewById(R.id.button1);


        //link alla schermata d'inserimento Foto  ****Bisogna Modificare col secondo Fragment, al momento Assente***

        btnLinkToPhoto.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ApartmentEntryPhotoFragment apartmentEntryPhotoFragment = new ApartmentEntryPhotoFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryPhotoFragment, "Foto");
            }
        });


        return view;
    }


}
