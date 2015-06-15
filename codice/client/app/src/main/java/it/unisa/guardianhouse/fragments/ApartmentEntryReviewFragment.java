package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
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

    private static final String TAG = ApartmentEntryFragment.class.getSimpleName();
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
    private EditText inputReview;
    private RatingBar rateFurniture;
    private RatingBar rateThermic;
    private RatingBar rateLandlord;
    private RatingBar rateSecurity;
    private RatingBar rateBusNear;
    private RatingBar rateNeighbours;
    private RatingBar rateExperience;
    private RatingBar rateConditions;
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

                if (!description.isEmpty()) {

                    params = new HashMap<>();

                    //apartmentEntry
                    params.put("myTitle", bundle.getString("name"));
                    params.put("myDescript", bundle.getString("description"));
                    params.put("myMeters", bundle.getString("mq"));
                    params.put("myCarspot", bundle.getString("car_place"));
                    params.put("myContract", bundle.getString("contract_time"));
                    // cost
                    params.put("myConditions", bundle.getString("status"));

                    params.put("myCivic", bundle.getString("street_number"));
                    params.put("myInter", bundle.getString("intern_id"));
                    params.put("myLocality", bundle.getString("locality"));
                    params.put("myRoad", bundle.getString("route"));
                    //administrative area level 1
                    //administrative area level 2
                    //postal code
                    //country

                    params.put("myLatitude", bundle.getString("latitude"));
                    params.put("myLongitude", bundle.getString("longitude"));

                    //apartmentEntryRoom
                    //params.put("myGuest", bundle.getString("guest"));
                    //params.put("myRoom", bundle.getString("room"));
                    params.put("myFree", bundle.getString("free_rooms"));
                    //params.put("myBed", bundle.getString("bed"));
                    //params.put("myWc", bundle.getString("wc"));

                    //apartmentEntryReview
                    params.put("myConditions", String.valueOf(house_conditions));
                    params.put("myThermic", String.valueOf(thermic_capacity));
                    params.put("myLandlord", String.valueOf(landlord_honesty));
                    params.put("mySecurity", String.valueOf(security_level));
                    params.put("myBusNear", String.valueOf(bus_connection));
                    params.put("myNeighbours", String.valueOf(neighbours));
                    //distance_cc ?
                    params.put("myFurniture", String.valueOf(furniture_quality));
                    params.put("myReview", description);
                    params.put("myExperience", String.valueOf(rating));
                    params.put("user_id", session.getUserId());
                    params.put("username", session.getUsername());

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


                try {
                    Boolean error = response.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        Toast.makeText(getActivity(),
                                "Appartamento inserito!", Toast.LENGTH_LONG).show();

                        HomeFragment homeFragment = new HomeFragment();
                        ((MaterialNavigationDrawer) getActivity()).setFragment(homeFragment, "Home");

                    } else {
                        // Get the error message
                        //String errorMsg = response.getString("error_msg");
                        //Toast.makeText(getActivity(),
                        //        errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

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