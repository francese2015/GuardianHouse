package it.unisa.guardianhouse.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;


public class InsertReviewFragment extends Fragment {

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
    String userId;
    private double furniture_quality;
    private double thermic_capacity;
    private double landlord_honesty;
    private double security_level;
    private double bus_connection;
    private double neighbours;
    private double rating;
    private double house_conditions;







    public InsertReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_review, container, false);

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

        // url = "http://carlo.teammolise.rocks/api/apartments/5538b19fe4b07a8290702638/reviews/5538b1e6a4832c04cba15865";
        url = Config.APARTMENTS_URL + "/" + aptId + "/reviews" + "/:id";

        getReview();
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


                    ApartmentFragment apartmentFragment = new ApartmentFragment();
                    ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentFragment, "Scheda Appartamento");

                } else {
                    Toast.makeText(getActivity(),
                            "Compila la Descrizione!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return view;
    }


    public void getReview() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject singleReview = response.getJSONObject("review");
                    inputTxtReview.setText(singleReview.getString("description"));

                    String stringRating = String.valueOf(singleReview.getDouble("house_conditions"));
                    ratingBarConditions.setRating(Float.parseFloat(stringRating));

                    String stringFurniture = String.valueOf(singleReview.getDouble("furniture_quality"));
                    ratingBarFurniture.setRating(Float.parseFloat(stringFurniture));

                    String stringThermic = String.valueOf(singleReview.getDouble("thermic_capacity"));
                    ratingBarThermic.setRating(Float.parseFloat(stringThermic));

                    String stringHonesty = String.valueOf(singleReview.getDouble("landlord_honesty"));
                    ratingBarHonesty.setRating(Float.parseFloat(stringHonesty));

                    String stringBus = String.valueOf(singleReview.getDouble("bus_connection"));
                    ratingBarBus.setRating(Float.parseFloat(stringBus));

                    String stringSecurity = String.valueOf(singleReview.getDouble("security_level"));
                    ratingBarSecurity.setRating(Float.parseFloat(stringSecurity));

                    String stringNeighbours = String.valueOf(singleReview.getDouble("neighbours"));
                    ratingBarNeighbours.setRating(Float.parseFloat(stringNeighbours));

                    String stringCcDistance = String.valueOf(singleReview.getDouble("distance_cc"));
                    ratingBarCcDistance.setRating(Float.parseFloat(stringCcDistance));

                  //  releasedValue.setText(singleReview.getJSONObject("released_by").getString("username"));

                   // userId = singleReview.getJSONObject("released_by").getString("user_id");



                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq);
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
