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

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryReviewFragment extends Fragment {

    ButtonRectangle btnToSave;
    private EditText inputReview;
    private RatingBar rateFurniture;
    private RatingBar rateThermic;
    private RatingBar rateLandlord;
    private RatingBar rateSecurity;
    private RatingBar rateBusNear;
    private RatingBar rateNeighbours;
    private RatingBar rateExperience;
    private RatingBar rateConditions;

    String review;
    double furniture;
    double thermic;
    double landlord;
    double security;
    double bus;
    double neigh;
    double exp;
    double conditions;


    Bundle bundle ;


    public ApartmentEntryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_3_review, container, false);

        btnToSave = (ButtonRectangle) view.findViewById(R.id.button_to_save);
        inputReview = (EditText) view.findViewById(R.id.txtReview);
        rateFurniture = (RatingBar) view.findViewById(R.id.ratingBar1);
        rateThermic = (RatingBar) view.findViewById(R.id.ratingBar2);
        rateLandlord = (RatingBar) view.findViewById(R.id.ratingBar3);
        rateSecurity = (RatingBar) view.findViewById(R.id.ratingBar4);
        rateBusNear = (RatingBar) view.findViewById(R.id.ratingBar5);
        rateNeighbours = (RatingBar) view.findViewById(R.id.ratingBar6);
        rateExperience = (RatingBar) view.findViewById(R.id.ratingBar7);
        rateConditions = (RatingBar) view.findViewById(R.id.ratingBar8);



        btnToSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                review = inputReview.getText().toString();
                furniture = rateFurniture.getNumStars();
                thermic = rateThermic.getNumStars();
                landlord = rateLandlord.getNumStars();
                security = rateSecurity.getNumStars();
                bus = rateBusNear.getNumStars();
                neigh = rateNeighbours.getNumStars();
                exp = rateExperience.getNumStars();
                conditions = rateConditions.getNumStars();

                if(!review.isEmpty()){

                bundle = getArguments();
                bundle.putString("myReview", review);
                bundle.putDouble("myFurniture", furniture);
                bundle.putDouble("myThermic", thermic);
                bundle.putDouble("myLandlord", landlord);
                bundle.putDouble("mySecurity", security);
                bundle.putDouble("myBusNear", bus);
                bundle.putDouble("myNeighbours", neigh);
                bundle.putDouble("myExperience", exp);
                bundle.putDouble("myConditions", conditions);


                    Toast.makeText(getActivity(),
                            "Appartamento Inserito Correttamente :) ", Toast.LENGTH_LONG).show();

                    HomeFragment homeFragment = new HomeFragment();
                    ((MaterialNavigationDrawer) getActivity()).setFragment(homeFragment, "Home");

                } else {


                    Toast.makeText(getActivity(),
                            "Riempi la Recensione!", Toast.LENGTH_LONG)
                            .show();


                }

            }

        });


        return view;
    }


}
