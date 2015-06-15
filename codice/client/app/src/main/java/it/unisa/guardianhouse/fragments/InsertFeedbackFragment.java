package it.unisa.guardianhouse.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by thecomputerwhisper on 14/06/15.
 */
public class InsertFeedbackFragment extends Fragment{

    private static final String TAG = InsertFeedbackFragment.class.getSimpleName();
    private SessionManager session;
    private EditText feedDescription;
    private RatingBar feedRating;
    private String feedUserIdof;
    private String url;
    private String feedUsernameof;
    private ButtonRectangle btnSend;
    private String insertID;
    private Bundle bundle;
    private String description;
    private Float rating;
    Map<String, String> params;

    public InsertFeedbackFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_feedback, container, false);

        session = new SessionManager(getActivity());

        feedDescription = (EditText) view.findViewById(R.id.feedback_insert_txt);
        feedRating = (RatingBar) view.findViewById(R.id.ratingBar_insert_feed);
        btnSend = (ButtonRectangle) view.findViewById(R.id.btn_send);

        Bundle b = getArguments();
        feedUserIdof = b.getString("user_id");
        feedUsernameof = b.getString("username");

        url = Config.USERS_URL + "/" + feedUserIdof + "/feedbacks";

        btnSend.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {

                description = feedDescription.getText().toString();
                rating = feedRating.getRating();


                if (!description.isEmpty()) {
                    // fare qui la richiesta

                    params = new HashMap<String, String>();
                    params.put("description", description);
                    params.put("rating", String.valueOf(rating));
                    params.put("user_id", feedUserIdof);   //ID e USERNAME di chi riceve il feedback
                    //params.put("username",feedUsernameof);
                    params.put("user_id", session.getUserId());   //ID e USERNAME di chi lascia il feedback
                    params.put("username", session.getUsername());

                    makeRequest();

                } else {
                    Toast.makeText(getActivity(),
                            "Inserisci una Descrizione", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return view;
    }

    private void makeRequest() {

        String tag_jObj_req = "req_insert_feedback";

        MyJsonObjectRequest strReq = new MyJsonObjectRequest(Request.Method.POST,
                url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Insert Feedback Response: " + response.toString());


                try {
                    Boolean error = response.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        Toast.makeText(getActivity(),
                                "Feedback Inserito!", Toast.LENGTH_LONG).show();

                        FeedbackListFragment feedbackListFragment = new FeedbackListFragment();
                        ((MaterialNavigationDrawer) getActivity()).setFragment(feedbackListFragment, "Feedbacks");

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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
