package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryRoomFragment extends Fragment implements View.OnClickListener {

    ButtonRectangle btnLinkToReview;
    Button btnLessGuests;
    Button btnPlusGuests;
    Button btnLessRooms;
    Button btnPlusRooms;
    Button btnLessFree;
    Button btnPlusFree;
    Button btnLessBed;
    Button btnPlusBed;
    Button btnLessWc;
    Button btnPlusWc;
    EditText guestScoreText;
    EditText roomScoreText;
    EditText freeScoreText;
    EditText bedScoreText;
    EditText wcScoreText;
    int counter = 0;
    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;
    int counter4 = 0;

    String guest;
    String room;
    String free_rooms;
    String bed;
    String wc;


    Bundle bundle;




    public ApartmentEntryRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_2_room, container, false);

        btnLinkToReview = (ButtonRectangle) view.findViewById(R.id.button_to_review);
        btnLessRooms = (Button) view.findViewById(R.id.button_less_rooms);
        btnPlusRooms = (Button) view.findViewById(R.id.button_plus_rooms);
        btnLessFree = (Button) view.findViewById(R.id.button_less_free);
        btnPlusFree = (Button) view.findViewById(R.id.button_plus_free);
        btnLessGuests = (Button) view.findViewById(R.id.button_less_guests);
        btnPlusGuests = (Button) view.findViewById(R.id.button_plus_guests);
        btnLessBed = (Button) view.findViewById(R.id.button_less_bed);
        btnPlusBed = (Button) view.findViewById(R.id.button_plus_bed);
        btnLessWc = (Button) view.findViewById(R.id.button_less_wc);
        btnPlusWc = (Button) view.findViewById(R.id.button_plus_wc);
        guestScoreText = (EditText) view.findViewById(R.id.integer_guests);
        roomScoreText = (EditText) view.findViewById(R.id.integer_room);
        freeScoreText = (EditText) view.findViewById(R.id.integer_free_room);
        bedScoreText = (EditText) view.findViewById(R.id.integer_bed);
        wcScoreText = (EditText) view.findViewById(R.id.integer_wc);


        btnPlusRooms.setOnClickListener(this);
        btnLessRooms.setOnClickListener(this);
        btnLessFree.setOnClickListener(this);
        btnPlusFree.setOnClickListener(this);
        btnLessGuests.setOnClickListener(this);
        btnPlusGuests.setOnClickListener(this);
        btnLessBed.setOnClickListener(this);
        btnPlusBed.setOnClickListener(this);
        btnLessWc.setOnClickListener(this);
        btnPlusWc.setOnClickListener(this);





        //link al Fragment Recensione
        btnLinkToReview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                guest = guestScoreText.getText().toString();
                room = roomScoreText.getText().toString();
                free_rooms = freeScoreText.getText().toString();
                bed = bedScoreText.getText().toString();
                wc = wcScoreText.getText().toString();


                if(!guest.matches("0") && !room.matches("0") && !free_rooms.matches("0")  && !bed.matches("0") && !wc.matches("0")){



                bundle = getArguments();
                bundle.putString("myGuest", guest);
                bundle.putString("myRoom", room);
                bundle.putString("myFree", free_rooms);
                bundle.putString("myBed", bed);
                bundle.putString("myWc", wc);

                ApartmentEntryReviewFragment apartmentEntryReviewFragment = new ApartmentEntryReviewFragment();
                apartmentEntryReviewFragment.setArguments(bundle);

                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryReviewFragment, "Inserisci una Recensione");


                } else {


                    Toast.makeText(getActivity(),
                            "Incrementa ogni campo", Toast.LENGTH_LONG)
                            .show();


                }

            }

        });


        return view;
    }



    @Override
    public void onClick(View v) {

        if (v == btnPlusRooms){
            counter++;
            roomScoreText.setText(Integer.toString(counter));

        }
        if (v == btnLessRooms){
            counter--;
            roomScoreText.setText(Integer.toString(counter));

                   }
        if (v == btnPlusFree){
            counter1++;
            freeScoreText.setText(Integer.toString(counter1));


        }
        if (v == btnLessFree){
            counter1--;
            freeScoreText.setText(Integer.toString(counter1));

        }
        if (v == btnPlusGuests){
            counter2++;
            guestScoreText.setText(Integer.toString(counter2));


        }
        if (v == btnLessGuests){
            counter2--;
            guestScoreText.setText(Integer.toString(counter2));

        }
        if (v == btnPlusBed){
            counter3++;
            bedScoreText.setText(Integer.toString(counter3));


        }
        if (v == btnLessBed){
            counter3--;
            bedScoreText.setText(Integer.toString(counter3));

        }
        if (v == btnPlusWc){
            counter4++;
            wcScoreText.setText(Integer.toString(counter4));


        }
        if (v == btnLessWc){
            counter4--;
            wcScoreText.setText(Integer.toString(counter4));

        }


    }
}
