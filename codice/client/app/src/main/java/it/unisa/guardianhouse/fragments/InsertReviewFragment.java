package it.unisa.guardianhouse.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
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
import it.unisa.guardianhouse.NavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.helpers.SessionManager;
import it.unisa.guardianhouse.utils.MyJsonObjectRequest;


public class InsertReviewFragment extends Fragment {

    private static final String TAG = InsertReviewFragment.class.getSimpleName();
    private RatingBar ratingBarHonesty;
    private RatingBar ratingBarSecurity;
    private RatingBar ratingBarBus;
    private RatingBar ratingBarConditions;
    private RatingBar ratingBarNeighbours;
    private String url;
    private String aptId;
    private EditText inputTxtReview;
    private TextView releasedValue;
    private RatingBar ratingBarFurniture;
    private RatingBar ratingBarThermic;
    private RatingBar ratingBarCcDistance;
    private ButtonRectangle btnSave;
    private String txtReview;
    Bundle bundle;
    double furniture_quality;
    double thermic_capacity;
    double landlord_honesty;
    double security_level;
    double bus_connection;
    double neighbours;
    double rating;
    double house_conditions;
    Map<String, String> params;
    SessionManager session;


    public InsertReviewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_review, container, false);

        session = new SessionManager(getActivity());

        inputTxtReview = (EditText) view.findViewById(R.id.txtReview);
        ratingBarFurniture = (RatingBar) view.findViewById(R.id.ratingBarFurniture);
        ratingBarThermic = (RatingBar) view.findViewById(R.id.ratingBarThermic);
        ratingBarHonesty = (RatingBar) view.findViewById(R.id.ratingBarHonesty);
        ratingBarSecurity = (RatingBar) view.findViewById(R.id.ratingBarSecurity);
        ratingBarBus = (RatingBar) view.findViewById(R.id.ratingBarBus);
        ratingBarConditions = (RatingBar) view.findViewById(R.id.ratingBarConditions);
        ratingBarNeighbours = (RatingBar) view.findViewById(R.id.ratingBarNeighbours);
        ratingBarCcDistance = (RatingBar) view.findViewById(R.id.ratingBarCcDistance);
        releasedValue = (TextView) view.findViewById(R.id.released_value);
        btnSave = (ButtonRectangle) view.findViewById(R.id.btn_save);

        Bundle b = getArguments();
        aptId = b.getString("AptId");

        url = Config.APARTMENTS_URL + "/" + aptId + "/reviews";


        btnSave.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {

                txtReview = inputTxtReview.getText().toString();
                furniture_quality = ratingBarFurniture.getNumStars();
                thermic_capacity = ratingBarThermic.getNumStars();
                landlord_honesty = ratingBarHonesty.getNumStars();
                security_level = ratingBarSecurity.getNumStars();
                bus_connection = ratingBarBus.getNumStars();
                neighbours = ratingBarNeighbours.getNumStars();
                rating = ratingBarCcDistance.getNumStars();
                house_conditions = ratingBarConditions.getNumStars();


                if (!txtReview.isEmpty()) {
                    // fare qui la richiesta

                    params = new HashMap<String, String>();
                    params.put("description", txtReview);
                    params.put("furniture_quality", String.valueOf(furniture_quality));
                    params.put("thermic_capacity", String.valueOf(thermic_capacity));
                    params.put("landlord_honesty", String.valueOf(landlord_honesty));
                    params.put("security_level", String.valueOf(thermic_capacity));
                    params.put("bus_connection", String.valueOf(bus_connection));
                    params.put("neighbours", String.valueOf(thermic_capacity));
                    params.put("rating", String.valueOf(rating));
                    params.put("house_conditions", String.valueOf(house_conditions));
                    params.put("user_id", session.getUserId());
                    params.put("username", session.getUsername());

                    makeRequest();

                } else {
                    Toast.makeText(getActivity(),
                            "Compila la Descrizione!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return view;
    }


    private void makeRequest() {
        // Tag used to cancel the request
        String tag_jObj_req = "req_insert_review";

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
                                "Recensione inserita!", Toast.LENGTH_LONG).show();

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
                headers.put("Content-Type","application/x-www-form-urlencoded");
                headers.put("Authorization", session.getApiKey());
                return headers;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_jObj_req);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
