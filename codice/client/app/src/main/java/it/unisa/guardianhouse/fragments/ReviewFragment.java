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

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public ImageView imgSecurity;
    Bundle bundle;
    private TextView honestyLabel;
    private ImageView imgHonesty;
    private RatingBar ratingBarHonesty;
    private TextView securityLabel;
    private RatingBar ratingBarSecurity;
    private TextView durationLabel;
    private TextView duration;
    private TextView busLabel;
    private ImageView imgBus;
    private RatingBar ratingBarBus;
    private TextView conditionsLabel;
    private ImageView imgConditions;
    private RatingBar ratingBarConditions;
    private TextView neighboursLabel;
    private ImageView imgNeighbours;
    private RatingBar ratingBarNeighbours;
    private TextView ccLabel;
    private ImageView imgCc;
    private ListView listView;
    private ImageView imgView;
    private String url;
    private String aptId;
    private TextView textLabel;
    private TextView addressValue;
    private TextView txtReview;
    private TextView releasedLabel;
    private TextView releasedValue;
    private TextView furnitureLabel;
    private ImageView imgFurniture;
    private RatingBar ratingBarFurniture;
    private TextView thermicLabel;
    private ImageView imgThermic;
    private RatingBar ratingBarThermic;
    private RatingBar ratingBarCcDistance;
    private ButtonRectangle btnBack;


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
        thermicLabel = (TextView) view.findViewById(R.id.thermic_label);
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
        conditionsLabel = (TextView) view.findViewById(R.id.conditions_label);
        imgConditions = (ImageView) view.findViewById(R.id.img_conditions);
        ratingBarConditions = (RatingBar) view.findViewById(R.id.ratingBarConditions);
        neighboursLabel = (TextView) view.findViewById(R.id.neighbours_vote_label);
        imgNeighbours = (ImageView) view.findViewById(R.id.img_neighbours);
        ratingBarNeighbours = (RatingBar) view.findViewById(R.id.ratingBarNeighbours);

        ccLabel = (TextView) view.findViewById(R.id.cc_vote_label);
        imgCc = (ImageView) view.findViewById(R.id.img_cc);
        ratingBarCcDistance = (RatingBar) view.findViewById(R.id.ratingBarCcDistance);

        releasedLabel = (TextView) view.findViewById(R.id.released_label);
        releasedValue = (TextView) view.findViewById(R.id.released_value);

        btnBack = (ButtonRectangle) view.findViewById(R.id.button_to_review);


        Bundle bundle = getArguments();
        aptId = bundle.getString("myAptId");

        url = "http://carlo.teammolise.rocks/api/apartments/5538b19fe4b07a8290702638/reviews/5538b1e6a4832c04cba15865";
        // url = Config.APARTMENTS_URL + "/" + aptId + "/reviews" + "/:id";


        getReview();


        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ReviewListFragment reviewListFragment = new ReviewListFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(reviewListFragment, "Lista recensioni");
            }
        });
        return view;
    }


    public void getReview() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject singleReview;

                        singleReview = response.getJSONObject("received_reviews");
                        txtReview.setText(singleReview.getJSONObject("received_reviews").getString("description"));

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

                        singleReview = response.getJSONObject("received_reviews");
                        releasedValue.setText(singleReview.getJSONObject("released_by").getString("username"));

                        String userId = singleReview.getJSONObject("released_by").getString("user_id");


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
