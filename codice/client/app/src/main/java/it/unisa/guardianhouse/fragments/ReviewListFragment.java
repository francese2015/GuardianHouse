package it.unisa.guardianhouse.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ReviewListAdapter;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Review;

/**
 * Created by Luigi on 26/05/2015.
 */
public class ReviewListFragment extends Fragment {

    private static String TAG = ReviewListFragment.class.getSimpleName();
    private ListView listView;
    ReviewListAdapter adapter;
    ArrayList<Review> reviewList;
    String aptId;
    String url;


    public ReviewListFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_list, container, false);

        Bundle bundle = getArguments();
        aptId = bundle.getString("myAptId");
        url = "http://carlo.teammolise.rocks/api/apartments/554732e9e4b08dbaf0160eec/reviews";
        reviewList = new ArrayList<Review>();

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ReviewListAdapter(getActivity(),reviewList);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//
//                //url = Config.APARTMENTS_URL + "/" + aptId + "/reviews";
//
//                ReviewFragment reviewFragment = new ReviewFragment();
//                ((MaterialNavigationDrawer) getActivity()).setFragment(reviewFragment, "Recensione");
//            }
//        });

        getReviews();

        return view;
    }

    public void getReviews() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray reviewsList = response.getJSONArray("received_reviews");
                    for (int i = 0; i < reviewsList.length(); i++) {
                        JSONObject singleReview = reviewsList.getJSONObject(i);
                        Review review = new Review();
//                      review.setRewId(singleReview.getJSONObject("_id").getString("$id"));
//                      review.setApplicanceStatus(singleReview.getDouble("house_conditions"));
//                      review.setThermicCapacity(singleReview.getDouble("thermic_capacity"));
//                      review.setLandlordHonesty(singleReview.getDouble("landlord_honesty"));
//                      review.setSecurityLevel(singleReview.getDouble("security_level"));
//                      review.setBusConnection(singleReview.getDouble("bus_connection"));
//                      review.setNeighbours(singleReview.getDouble("neighbours"));
//                      review.setDistanceCC(singleReview.getDouble("distance_cc"));
//                      review.setFornitureQuality(singleReview.getDouble("furniture_quality"));
//                      review.setFeedbackRate(singleReview.getDouble("rating"));
                        review.setDescription(singleReview.getString("description"));
                        review.setUsername(singleReview.getJSONObject("released_by").getString("username"));
                        //String userId = singleReview.getJSONObject("released_by").getString("user_id");
                        //String thumbnailUrl = "http://carlo.teammolise.rocks/img/usr/" + userId + ".png";
                        String thumbnailUrl = "http://carlo.teammolise.rocks/img/usr/default.png";
                        review.setThumbnailUrl(thumbnailUrl);
                        reviewList.add(review);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

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
        reviewList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
