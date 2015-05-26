package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ApartmentListAdapter;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;
import it.unisa.guardianhouse.utils.LocationTracker;
import it.unisa.guardianhouse.utils.Utils;


public class HomeFragment extends Fragment {

    private static String TAG = HomeFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;

    SwipeRefreshLayout mSwipeRefreshLayout;

    LocationTracker gps;
    Double latitude;
    Double longitude;
    Double distance = 10.0;

    private ListView listView;
    ApartmentListAdapter adapter;
    ArrayList<Apartment> apartmentList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apartmentList = new ArrayList<Apartment>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(getActivity(), apartmentList);
        listView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        apartmentList.clear();
                        adapter.notifyDataSetChanged();
                        startRequest();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Object obj = adapter.getItemAtPosition(position);
                Apartment apt = (Apartment) obj;
                Bundle b = new Bundle();
                b.putString("aptId", apt.getId());
                b.putDouble("itemLatitude", apt.getLatitude());
                b.putDouble("itemLongitude", apt.getLongitude());
                b.putDouble("distance", apt.getDistanceFromLocation());
                ApartmentFragment aptFragment = new ApartmentFragment();
                aptFragment.setArguments(b);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(aptFragment, "Scheda appartamento");
            }
        });

        startRequest();

        return view;
    }

    public void startRequest() {
        if (Utils.hasConnection(getActivity()) == true) {
            gps = new LocationTracker(getActivity());

            // check if GPS enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                url = Config.FEATURED_APT_URL + "/" + latitude + "," + longitude + "," + distance;

                searchByLocation();

            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
        } else {
            Toast.makeText(getActivity(),
                    "Attezione! Attiva la connessione.", Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void searchByLocation() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apartmentArray = response.getJSONArray("apartments");
                    for (int i = 0; i < apartmentArray.length(); i++) {
                        JSONObject singleApartment = apartmentArray.getJSONObject(i);
                        Apartment apartment = new Apartment();

                        //ottengo il nome appartamento
                        apartment.setId(singleApartment.getJSONObject("_id").getString("$id"));
                        apartment.setThumbnailUrl(singleApartment.getJSONArray("pictures").getJSONObject(0).getString("url"));
                        apartment.setName(singleApartment.getJSONObject("details").getString("name"));
                        apartment.setFeatured(singleApartment.getJSONObject("details").getBoolean("featured"));
                        apartment.setInternId(singleApartment.getJSONObject("address").getString("intern_id"));
                        apartment.setStreetNumber(singleApartment.getJSONObject("address").getString("street_number"));
                        apartment.setRoute(singleApartment.getJSONObject("address").getString("route"));
                        apartment.setLocality(singleApartment.getJSONObject("address").getString("locality"));
                        apartment.setAdminAreaLevel1(singleApartment.getJSONObject("address").getString("administrative_area_level_1"));
                        apartment.setAdminAreaLevel2(singleApartment.getJSONObject("address").getString("administrative_area_level_2"));
                        apartment.setPostalCode(singleApartment.getJSONObject("address").getString("postal_code"));
                        apartment.setCountry(singleApartment.getJSONObject("address").getString("country"));

                        //ottengo il rating
                        String stringRating = singleApartment.getString("average_rating");
                        apartment.setRating(Float.parseFloat(stringRating));

                        apartment.setLatitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(1));
                        apartment.setLongitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(0));
                        apartment.setDistanceFromLocation(singleApartment.getDouble("distance"));
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
        apartmentList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
