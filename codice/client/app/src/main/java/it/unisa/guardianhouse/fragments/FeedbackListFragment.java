package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.FeedbackListAdapter;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.helpers.SessionManager;
import it.unisa.guardianhouse.models.Feedback;


public class FeedbackListFragment extends Fragment {

    private static String TAG = FeedbackListFragment.class.getSimpleName();
    private ListView listView;
    private FeedbackListAdapter adapter;
    private ArrayList<Feedback> feedbacksList;
    private String username_feed_of;
    private SessionManager session;
    String usrId;
    String url;


    public FeedbackListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

        session = new SessionManager(getActivity());

        if (usrId != session.getUserId() && session.isLoggedIn()) {
            setHasOptionsMenu(true);
        } else {
            setHasOptionsMenu(false);
        }

        Bundle bundle = getArguments();
        usrId = bundle.getString("user_id");
        username_feed_of = bundle.getString("username");
        url = Config.USERS_URL + "/" + usrId + "/feedbacks";

        feedbacksList = new ArrayList<Feedback>();
        listView = (ListView) view.findViewById(R.id.listViewfeed);
        adapter = new FeedbackListAdapter(getActivity(),feedbacksList);
        listView.setAdapter(adapter);
        listView.setEmptyView(view.findViewById(R.id.empty_list));


        getFeedbacks();

        return view;
    }

    public void getFeedbacks() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray feedbacksArray = response.getJSONArray("receivedFeedbacks");
                    for (int i = 0; i < feedbacksArray.length(); i++) {
                        JSONObject singleFeedback = feedbacksArray.getJSONObject(i);
                        Feedback feedback = new Feedback();
                        feedback.setFeedback_id(singleFeedback.getJSONObject("_id").getString("$id"));
                        feedback.setUsername(singleFeedback.getJSONObject("given_by").getString("username"));
                        feedback.setRating(singleFeedback.getString("rating"));
                        feedback.setFeed_text(singleFeedback.getString("description"));

                        feedbacksList.add(feedback);
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
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        feedbacksList.clear();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_feedback, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_feedback:

                Bundle bundle = new Bundle();
                bundle.putString("user_id", usrId); //passo l'id della persona a cui si riferisce il feedback
                bundle.putString("username", username_feed_of);
                InsertFeedbackFragment insertFeedbackFragment = new InsertFeedbackFragment();
                insertFeedbackFragment.setArguments(bundle);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(insertFeedbackFragment, "Inserisci Feedback");

                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



}
