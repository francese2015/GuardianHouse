package it.unisa.guardianhouse.fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;
import it.unisa.guardianhouse.utils.LocationTracker;
import it.unisa.guardianhouse.utils.Utils;


public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ButtonFloat btnSearchAddress;
    private ButtonFlat btnSearchLocation;
    private SeekBar seekbarRadius;
    private Double latitude;
    private Double longitude;
    private int distance;
    LocationTracker gps;
    String autoCompText;
    Bundle bundle;

    private static String TAG = ResultsFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();

    private static final String LOG_TAG = "GuardianHouseApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    //------------ make your specific key ------------
    // chiave del tutorial
    private static final String API_KEY = "AIzaSyAU9ShujnIg3IDQxtPr7Q1qOvFVdwNmWc4";
    // chiave mia
    //private static final String API_KEY = "AIzaSyDtLLf-f6DzLyh4AYDu-V42SHFdx5DwN8c";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Context c = getActivity().getApplicationContext();

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final AutoCompleteTextView autoCompView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);

        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(getActivity(), R.layout.list_item_search));
        autoCompView.setOnItemClickListener(this);

        final TextView tvRadiusText = (TextView) view.findViewById(R.id.tvRadiusText);

        seekbarRadius = (SeekBar) view.findViewById(R.id.seekbarRadius);
        seekbarRadius.setMax(Config.MAX_SEARCH_RADIUS);
        seekbarRadius.setProgress(Config.MAX_SEARCH_RADIUS / 3);
        seekbarRadius.setEnabled(true);
        tvRadiusText.setText("Distanza: " + seekbarRadius.getProgress() + " Km");
        seekbarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub

                String strSeekVal = String.format("%s: %d %s",
                        "Distanza", progress,"Km");

                tvRadiusText.setText(strSeekVal);
            }
        });


        btnSearchAddress = (ButtonFloat) view.findViewById(R.id.search_img);
        btnSearchLocation = (ButtonFlat) view.findViewById(R.id.search_by_gps);

        // bottone search by address
        btnSearchAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Utils.hasConnection(getActivity()) == true) {
                    Geocoder gc = new Geocoder(getActivity());

                    autoCompText = autoCompView.getText().toString();
                    if (autoCompText.trim().length() > 0) {

                        if (gc.isPresent()) {
                            List<Address> list = null;
                            try {
                                list = gc.getFromLocationName(autoCompText, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Address address = list.get(0);

                            latitude = address.getLatitude();
                            longitude = address.getLongitude();
                            distance = seekbarRadius.getProgress();

                            url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

                            pDialog = new ProgressDialog(getActivity());
                            pDialog.setMessage("Ricerca in corso...");
                            pDialog.show();

                            searchByLocation();
                        }
                    } else {
                        // chiedo all'utente di inserire i dati
                        Toast.makeText(getActivity(),
                                "Inserisci l'indirizzo!", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(getActivity(),
                            "Attezione! Attiva la connessione.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // bottone search by location
        btnSearchLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Utils.hasConnection(getActivity()) == true) {
                    gps = new LocationTracker(getActivity());

                    // check if GPS enabled
                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        distance = seekbarRadius.getProgress();

                        url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

                        pDialog = new ProgressDialog(getActivity());
                        pDialog.setMessage("Ricerca in corso...");
                        pDialog.show();

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
        });

        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        //Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            //sb.append("&components=country:gr");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println("URL: "+url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
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
                    bundle = new Bundle();
                    bundle.putString("myAddress", autoCompText);
                    bundle.putDouble("myLatitude", latitude);
                    bundle.putDouble("myLongitude", longitude);
                    bundle.putInt("distance", distance);
                    bundle.putParcelableArrayList("aptData", apartmentList);

                    ResultsFragment searchResults = new ResultsFragment();
                    searchResults.setArguments(bundle);
                    ((MaterialNavigationDrawer) getActivity()).setFragmentChild(searchResults, "Risultati");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hidePDialog();
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        apartmentList.clear();
    }

}
