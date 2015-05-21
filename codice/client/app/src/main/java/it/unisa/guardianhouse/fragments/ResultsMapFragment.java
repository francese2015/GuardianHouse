package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;


public class ResultsMapFragment extends Fragment {

    private static String TAG = ResultsMapFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;
    private Double latitude;
    private Double longitude;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();


    public ResultsMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results_map, container, false);

        MapsInitializer.initialize(getActivity());

        Bundle b = getArguments();
        latitude = b.getDouble("latitude");
        longitude = b.getDouble("longitude");
        int distance = b.getInt("distance");

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(mBundle);


        url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Ricerca in corso...");
        pDialog.show();

        searchByLocation(view);

        return view;
    }


    public void searchByLocation(final View view) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try {
                    JSONArray apartmentArray = response.getJSONArray("apartments");
                    for (int i = 0; i < apartmentArray.length(); i++) {
                        JSONObject singleApartment = apartmentArray.getJSONObject(i);
                        Apartment apartment = new Apartment();
                        //ottengo il nome appartamento
                        apartment.setId(singleApartment.getJSONObject("_id").getString("$id"));
                        apartment.setName(singleApartment.getJSONObject("details").getString("name"));
                        apartment.setLatitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(1));
                        apartment.setLongitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(0));
                        apartmentList.add(apartment);
                    }
                    setUpMapIfNeeded(view);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = savedInstanceState;
    }

    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        for (int i = 0; i < apartmentList.size(); i++) {
            double lat = apartmentList.get(i).getLatitude();
            double lng = apartmentList.get(i).getLongitude();
            LatLng point = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(point).title(apartmentList.get(i).getName()));
        }
        LatLng center = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        hidePDialog();
        super.onDestroy();
    }


}
