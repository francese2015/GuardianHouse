package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Map;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ApartmentListAdapter;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;
import it.unisa.guardianhouse.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultsFragment extends Fragment {

    private static String TAG = SearchResultsFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();
    private ListView listView;
    ApartmentListAdapter adapter;

    public SearchResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(getActivity(), apartmentList);
        listView.setAdapter(adapter);

        Bundle b = getArguments();
        double latitude = b.getDouble("latitude");
        double longitude = b.getDouble("longitude");
        int distance = b.getInt("distance");

        url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Ricerca in corso...");
        pDialog.show();

        if (Utils.hasConnection(getActivity()) == true) {
            searchByLocation();
        } else  {
            // chiedo all'utente di inserire i dati
            Toast.makeText(getActivity(),
                    "Connessione assente! Attivare internet o wifi.", Toast.LENGTH_LONG)
                    .show();
        }

        return view;
    }

    public void searchByLocation() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try {
                    JSONArray apartmentArray = response.getJSONArray("result");
                    for (int i = 0; i < apartmentArray.length(); i++) {
                        JSONObject singleApartment = apartmentArray.getJSONObject(i);
                        Apartment apartment = new Apartment();
                        apartment.setDescription(singleApartment.getJSONObject("details").getString("name"));
                        apartmentList.add(apartment);
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
                hidePDialog();
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
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
