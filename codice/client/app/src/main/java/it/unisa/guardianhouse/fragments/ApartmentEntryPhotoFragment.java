package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryPhotoFragment extends Fragment {

    ButtonRectangle btnLinkToRoom;
    private EditText inputTitle;
    private EditText inputComment;
    private ImageSwitcher inputPicture;
    Bundle bundle ;

    String title;
    String comment;



    public ApartmentEntryPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_3_photo, container, false);

        btnLinkToRoom = (ButtonRectangle) view.findViewById(R.id.button_to_room);
        inputTitle = (EditText) view.findViewById(R.id.nameApt);
        inputPicture = (ImageSwitcher) view.findViewById(R.id.imgViewPhoto);
        inputComment = (EditText) view.findViewById(R.id.txt_view);


        //link al Fragment Room

        btnLinkToRoom.setOnClickListener(new View.OnClickListener() {



            public void onClick(View view) {


                title = inputTitle.getText().toString();
                comment = inputComment.getText().toString();

                if(!title.isEmpty() && !comment.isEmpty()){



                    bundle = getArguments();
                    bundle.putString("myTitle",  title);
                    bundle.putString("myComment", comment);

                    ApartmentEntryRoomFragment photoResults = new ApartmentEntryRoomFragment();
                    photoResults.setArguments(bundle);

                    ApartmentEntryRoomFragment apartmentEntryRoomFragment = new ApartmentEntryRoomFragment();
                    ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryRoomFragment, "Composizione Casa");


                } else {


                    Toast.makeText(getActivity(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();


                }

            }

        });


        return view;
    }


}
