package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.helpers.SessionManager;
import it.unisa.guardianhouse.utils.MyJsonObjectRequest;


public class ApartmentEntryReviewFragment extends Fragment {

    private static final String TAG = ApartmentEntryReviewFragment.class.getSimpleName();
    private ButtonRectangle btnToSave;
    private String description;
    private double furniture_quality;
    private double thermic_capacity;
    private double landlord_honesty;
    private double security_level;
    private double bus_connection;
    private double neighbours;
    private double rating;
    private double house_conditions;
    private double distance_cc;

    private EditText inputReview;
    private RatingBar rateFurniture;
    private RatingBar rateThermic;
    private RatingBar rateLandlord;
    private RatingBar rateSecurity;
    private RatingBar rateBusNear;
    private RatingBar rateNeighbours;
    private RatingBar rateExperience;
    private RatingBar rateConditions;
    private RatingBar rateDistanceCC;

    private HashMap<String, String> params;
    private String url;
    SessionManager session;
    Bundle bundle;


    public ApartmentEntryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3_review, container, false);

        session = new SessionManager(getActivity());

        bundle = getArguments();

        inputReview = (EditText) view.findViewById(R.id.txtReview);
        rateFurniture = (RatingBar) view.findViewById(R.id.ratingBar1);
        rateThermic = (RatingBar) view.findViewById(R.id.ratingBar2);
        rateLandlord = (RatingBar) view.findViewById(R.id.ratingBar3);
        rateSecurity = (RatingBar) view.findViewById(R.id.ratingBar4);
        rateBusNear = (RatingBar) view.findViewById(R.id.ratingBar5);
        rateNeighbours = (RatingBar) view.findViewById(R.id.ratingBar6);
        rateExperience = (RatingBar) view.findViewById(R.id.ratingBar7);
        rateConditions = (RatingBar) view.findViewById(R.id.ratingBar8);
        rateDistanceCC = (RatingBar) view.findViewById(R.id.ratingBar9);

        btnToSave = (ButtonRectangle) view.findViewById(R.id.button_to_save);

        btnToSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                url = Config.APARTMENTS_URL;

                description = inputReview.getText().toString();
                furniture_quality = rateFurniture.getRating();
                thermic_capacity = rateThermic.getRating();
                landlord_honesty = rateLandlord.getRating();
                security_level = rateSecurity.getRating();
                bus_connection = rateBusNear.getRating();
                neighbours = rateNeighbours.getRating();
                rating = rateExperience.getRating();
                house_conditions = rateConditions.getRating();
                distance_cc = rateDistanceCC.getRating();


                for (String key : bundle.keySet()) {
                    Log.d("Bundle Debug", key + " = \"" + bundle.get(key) + "\"");
                }


                if (description.isEmpty() == false) {

                    params = new HashMap<>();

                    //apartmentEntry
                    params.put("name", bundle.getString("myTitle"));
                    params.put("description", bundle.getString("myDescript"));
                    params.put("mq", bundle.getString("myMeters"));
                    params.put("car_place", bundle.getString("myCarspot"));
                    params.put("contract_time", bundle.getString("mycontract"));
                    params.put("cost", bundle.getString("myCost"));
                    params.put("free_rooms", bundle.getString("myFree"));
                    params.put("status", bundle.getString("myConditions"));

                    params.put("street_number", bundle.getString("myCivic"));
                    params.put("route", bundle.getString("myRoad"));
                    params.put("locality", bundle.getString("myCity"));
                    params.put("intern_id", bundle.getString("myInter"));

                    params.put("postal_code", bundle.getString("postal_code"));
                    //params.put("administrative_area_level_1", bundle.getString("admin_level"));
                    //params.put("administrative_area_level_2", bundle.getString("sub_admin_level"));
                    params.put("country", bundle.getString("country"));

                    params.put("latitude", String.valueOf(bundle.getDouble("myLat")));
                    params.put("longitude", String.valueOf(bundle.getDouble("myLong")));

                    //params.put("myGuest", bundle.getString("guest"));
                    //params.put("myRoom", bundle.getString("room"));
                    //params.put("myBed", bundle.getString("bed"));
                    //params.put("myWc", bundle.getString("wc"));

                    params.put("house_conditions", String.valueOf(house_conditions));
                    params.put("thermic_capacity", String.valueOf(thermic_capacity));
                    params.put("landlord_honesty", String.valueOf(landlord_honesty));
                    params.put("security_level", String.valueOf(security_level));
                    params.put("bus_connection", String.valueOf(bus_connection));
                    params.put("neighbours", String.valueOf(neighbours));
                    params.put("distance_cc", String.valueOf(distance_cc));
                    params.put("furniture_quality", String.valueOf(furniture_quality));
                    params.put("description2", description);
                    params.put("rating", String.valueOf(rating));
                    params.put("user_id", session.getUserId());
                    params.put("username", session.getUsername());

                    for (String key : params.keySet()) {
                        Log.d("Params Debug", key + " = \"" + params.get(key) + "\"");
                    }

                    makeRequest();

                } else {
                    Toast.makeText(getActivity(),
                            "Riempi la Recensione!", Toast.LENGTH_LONG)
                            .show();
                }
            }


        });
        return view;
    }


    private void makeRequest() {
        // Tag used to cancel the request
        String tag_jObj_req = "req_insert_apartment";

        MyJsonObjectRequest strReq = new MyJsonObjectRequest(Request.Method.POST,
                url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Insert Review Response: " + response.toString());

                Toast.makeText(getActivity(),
                        "Appartamento inserito!", Toast.LENGTH_LONG).show();

                HomeFragment homeFragment = new HomeFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragment(homeFragment, "In primo piano");


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", session.getApiKey());
                return headers;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_jObj_req);
    }

}