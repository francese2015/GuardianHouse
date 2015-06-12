package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import it.unisa.guardianhouse.adapters.FeedbackListAdapter;
import it.unisa.guardianhouse.models.Feedback;


public class FeedbackListFragment extends Fragment {

    private ListView listView;
    private FeedbackListAdapter adapter;
    private ArrayList<Feedback> feedbacksList;
    private Bundle bundle = getArguments();
    private String url;


    public FeedbackListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new FeedbackListAdapter(getActivity(),feedbacksList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                //feedId = bundle.getString("myAptId");
                url = "http://carlo.teammolise.rocks/api/users/55368a09d742c9f41400002a/feedbacks";

                //Config.APARTMENTS_URL + "/" + aptId + "/reviews";
                FeedbackListFragment feedbackListFragment = new FeedbackListFragment();
                        ((MaterialNavigationDrawer) getActivity()).setFragment(feedbackListFragment, "Feedbacks");
            }
        });

        // Bundle bundle = getArguments();
        //feedbacksList = bundle.getStringArrayList();


        return view;
    }

    public void getFeedbacks(final View view) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                feedbacksList = new ArrayList<>();
                try {
                    JSONArray feedbacksList = response.getJSONArray("received_feedbacks");
                    for (int i = 0; i < feedbacksList.length(); i++) {
                        JSONObject singleFeedback = feedbacksList.getJSONObject();
                        Feedback feedback = new Feedback();
                        feedback.setFeedback_id(singleFeedback.getJSONObject("_id").getString("$id"));
                        feedback.setRating(singleFeedback.getJSONObject("_id").getString("$rating"));

                        feedbacksList.add(feedback);
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }



}
