package it.unisa.guardianhouse.fragments;


import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
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
import java.util.List;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ApartmentListAdapter;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;


public class ResultsListFragment extends Fragment {

    private static String TAG = ResultsListFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();
    private ListView listView;
    ApartmentListAdapter adapter;

    public ResultsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_list, container, false);

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(getActivity(), apartmentList);
        listView.setAdapter(adapter);

        // listening to single list item on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Object obj = adapter.getItemAtPosition(position);
                Apartment apt = (Apartment)obj;
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

        Bundle b = getArguments();
        double latitude = b.getDouble("latitude");
        double longitude = b.getDouble("longitude");
        int distance = b.getInt("distance");

        url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Ricerca in corso...");
        pDialog.show();

        searchByLocation();

        return view;
    }

    public void searchByLocation() {

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
                        apartment.setThumbnailUrl(singleApartment.getJSONArray("pictures").getJSONObject(0).getString("url"));
                        apartment.setName(singleApartment.getJSONObject("details").getString("name"));
                        apartment.setFeatured(singleApartment.getJSONObject("details").getBoolean("featured"));
                        //ottengo il rating
                        String stringRating = singleApartment.getString("average_rating");
                        apartment.setRating(Float.parseFloat(stringRating));
                        apartment.setDistanceFromLocation(singleApartment.getDouble("distance"));
                        apartment.setLatitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(1));
                        apartment.setLongitude(singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(0));
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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_search_results, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // Do Fragment menu item stuff here
//                return true;
//            default:
//                break;
//        }
//        return false;
//    }

    @Override
    public void onResume() {
        super.onResume();
        apartmentList.clear();
        adapter.notifyDataSetChanged();
    }
}
