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
public class ReviewFragment extends Fragment {


    private ListView listView;
    ImageView imgView;


    TextView textLabel;
    TextView addressValue;
    TextView txtReview;

    TextView furnitureLabel;
    ImageView imgFurniture;
    RatingBar ratingBarFurniture;

    TextView thermicLabel;
    ImageView imgThermic;
    RatingBar ratingBarThermic;

    TextView honestyLabel;
    ImageView imgHonesty;
    RatingBar ratingBarHonesty;

    TextView securityLabel;
    ImageView imgSecurity;
    RatingBar ratingBarSecurity;

    TextView durationLabel;

    TextView duration;

    TextView busLabel;
    ImageView imgBus;
    RatingBar ratingBarBus;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_view, container, false);

        textLabel = (TextView) view.findViewById(R.id.address_label);
        addressValue = (TextView) view.findViewById(R.id.address_value);
        txtReview = (TextView) view.findViewById(R.id.txtReview);
        furnitureLabel = (TextView) view.findViewById(R.id.furniture_label);
        imgFurniture = (ImageView) view.findViewById(R.id.img_furniture);
        ratingBarFurniture = (RatingBar) view.findViewById(R.id.ratingBarFurniture);
        thermicLabel = (TextView) view.findViewById(R.id.thermic_label );
        imgThermic = (ImageView) view.findViewById(R.id.img_thermic);
        ratingBarThermic = (RatingBar) view.findViewById(R.id.ratingBarThermic);
        honestyLabel = (TextView) view.findViewById(R.id.honesty_label);
        imgHonesty = (ImageView) view.findViewById(R.id.img_honesty);
        ratingBarHonesty = (RatingBar) view.findViewById(R.id.ratingBarHonesty);
        securityLabel = (TextView) view.findViewById(R.id.secure_label);
        imgSecurity = (ImageView) view.findViewById(R.id.img_security);
        ratingBarSecurity = (RatingBar) view.findViewById(R.id.ratingBarSecurity);
        durationLabel = (TextView) view.findViewById(R.id.contract_time_label);
        duration = (TextView) view.findViewById(R.id.durata);
        busLabel = (TextView) view.findViewById(R.id.bus_label);
        imgBus = (ImageView) view.findViewById(R.id.img_bus);
        ratingBarBus = (RatingBar) view.findViewById(R.id.ratingBarBus);








        return view;
    }


}
