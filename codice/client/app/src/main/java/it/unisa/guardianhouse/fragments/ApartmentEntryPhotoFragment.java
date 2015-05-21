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
public class ApartmentEntryPhotoFragment extends Fragment {

    ButtonRectangle btnLinkToRoom;


    public ApartmentEntryPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_3_photo, container, false);

        btnLinkToRoom = (ButtonRectangle) view.findViewById(R.id.button_to_room);


        //link al Fragment Room

        btnLinkToRoom.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ApartmentEntryRoomFragment apartmentEntryRoomFragment = new ApartmentEntryRoomFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragment(apartmentEntryRoomFragment, "Composizione Casa");
            }
        });


        return view;

    }
}
