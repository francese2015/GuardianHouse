package it.unisa.guardianhouse.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ReviewListAdapter;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Review;

/**
 * Created by Luigi on 26/05/2015.
 */
public class ReviewListFragment extends Fragment {

    private ListView listView;
    private ReviewListAdapter adapter;
    private ArrayList<Review> reviewList;
    private Bundle bundle = getArguments();
    private String aptId;
    private String url;


    public ReviewListFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_list, container, false);


        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ReviewListAdapter(getActivity(),reviewList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                aptId = bundle.getString("myAptId");
                url = Config.APARTMENTS_URL + "/" + aptId + "/reviews";

                ReviewFragment reviewFragment = new ReviewFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragment(reviewFragment, "Recensione");
            }
        });

        return view;
    }

    public void getReviews(final View view) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                reviewList = new ArrayList<>();
                try {
                    JSONArray reviewsList = response.getJSONArray("received_reviews");
                    for (int i = 0; i < reviewsList.length(); i++) {
                        JSONObject singleReview = reviewsList.getJSONObject(i);
                        Review review = new Review();
                        review.setRewId(singleReview.getJSONObject("_id").getString("$id"));
                        review.setApplicanceStatus(singleReview.getDouble("appliance_status"));
                        review.setThermicCapacity(singleReview.getDouble("thermic_capacity"));
                        review.setLandlordHonesty(singleReview.getDouble("landlord_honesty"));
                        review.setSecurityLevel(singleReview.getDouble("security_level"));
                        review.setBusConnection(singleReview.getDouble("bus_connection"));
                        review.setNeighbours(singleReview.getDouble("neighbours"));
                        review.setDistanceCC(singleReview.getDouble("distance_cc"));
                        review.setFornitureQuality(singleReview.getDouble("furniture_quality"));
                        review.setDescription(singleReview.getString("description"));
                        review.setFeedbackRate(singleReview.getDouble("rating"));
                        review.setUsername(singleReview.getString("released_by"));
                        reviewList.add(review);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    };


    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
