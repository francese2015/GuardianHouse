package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryReviewFragment extends Fragment {

    ButtonRectangle btnToSave;
    String description;
    double furniture_quality;
    double thermic_capacity;
    double landlord_honesty;
    double security_level;
    double bus_connection;
    double neighbours;
    double rating;
    double house_conditions;
    Bundle bundle;
    private EditText inputReview;
    private RatingBar rateFurniture;
    private RatingBar rateThermic;
    private RatingBar rateLandlord;
    private RatingBar rateSecurity;
    private RatingBar rateBusNear;
    private RatingBar rateNeighbours;
    private RatingBar rateExperience;
    private RatingBar rateConditions;
    private HashMap<String, String> params;
    private String url;
    private String aptId;
    private ProgressDialog pDialog;
    private String civic;


    public ApartmentEntryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_3_review, container, false);

        btnToSave = (ButtonRectangle) view.findViewById(R.id.button_to_save);
        inputReview = (EditText) view.findViewById(R.id.txtReview);
        rateFurniture = (RatingBar) view.findViewById(R.id.ratingBar1);
        rateThermic = (RatingBar) view.findViewById(R.id.ratingBar2);
        rateLandlord = (RatingBar) view.findViewById(R.id.ratingBar3);
        rateSecurity = (RatingBar) view.findViewById(R.id.ratingBar4);
        rateBusNear = (RatingBar) view.findViewById(R.id.ratingBar5);
        rateNeighbours = (RatingBar) view.findViewById(R.id.ratingBar6);
        rateExperience = (RatingBar) view.findViewById(R.id.ratingBar7);
        rateConditions = (RatingBar) view.findViewById(R.id.ratingBar8);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);


        btnToSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                url = Config.APARTMENTS_URL  ;

                description = inputReview.getText().toString();
                furniture_quality = rateFurniture.getNumStars();
                thermic_capacity = rateThermic.getNumStars();
                landlord_honesty = rateLandlord.getNumStars();
                security_level = rateSecurity.getNumStars();
                bus_connection = rateBusNear.getNumStars();
                neighbours = rateNeighbours.getNumStars();
                rating = rateExperience.getNumStars();
                house_conditions = rateConditions.getNumStars();

                if (!description.isEmpty()) {

                    bundle = getArguments();

                    bundle.putString("myReview", description);
                    bundle.putDouble("myFurniture", furniture_quality);
                    bundle.putDouble("myThermic", thermic_capacity);
                    bundle.putDouble("myLandlord", landlord_honesty);
                    bundle.putDouble("mySecurity", security_level);
                    bundle.putDouble("myBusNear", bus_connection);
                    bundle.putDouble("myNeighbours", neighbours);
                    bundle.putDouble("myExperience", rating);
                    bundle.putDouble("myConditions", house_conditions);

                    params = new HashMap<>();
                    //apartmentEntryReview
                    params.put("myReview", description);
                    params.put("myFurniture", String.valueOf(bundle.getDouble("furniture_quality")));
                    params.put("myThermic", String.valueOf(bundle.getDouble("thermic_capacity")));
                    params.put("myLandlord", String.valueOf(bundle.getDouble("landlord_honesty")));
                    params.put("mySecurity", String.valueOf(bundle.getDouble("security_level")));
                    params.put("myBusNear", String.valueOf(bundle.getDouble("bus_connection")));
                    params.put("myNeighbours", String.valueOf(bundle.getDouble("neighbours")));
                    params.put("myExperience", String.valueOf(bundle.getDouble("rating")));
                    params.put("myConditions", String.valueOf(bundle.getDouble("house_conditions")));

                    //apartmentEntry

                    params.put("myCivic", bundle.getString("street_number"));
                    params.put("myInter", bundle.getString("intern_id"));
                    params.put("myConditions", bundle.getString("status"));
                    params.put("myRoad", bundle.getString("route"));
                    params.put("myDescript", bundle.getString("description"));
                    params.put("myMeters", bundle.getString("mq"));
                    params.put("myCarspot", bundle.getString("car_place"));
                    params.put("myContract", bundle.getString("contract_time"));
                    params.put("myTitle", bundle.getString("name"));
                    params.put("myLocality", bundle.getString("locality"));


                    //apartmentEntryRoom
                    params.put("myGuest", bundle.getString("guest"));
                    params.put("myRoom", bundle.getString("room"));
                    params.put("myFree", bundle.getString("free_rooms"));
                    params.put("myBed", bundle.getString("bed"));
                    params.put("myWc", bundle.getString("wc"));


                    saveApartment();




                } else {


                    Toast.makeText(getActivity(),
                            "Riempi la Recensione!", Toast.LENGTH_LONG)
                            .show();


                }
            }

            public void saveApartment() {
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,
                        (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray apartmentArray = response.getJSONArray("apartments");
                            for (int i = 0; i < apartmentArray.length(); i++) {
                                JSONObject singleApartment = apartmentArray.getJSONObject(i);
                                Apartment apartment = new Apartment();

                                apartment.setId(singleApartment.getJSONObject("_id").getString("$id"));
                                apartment.setLocality(singleApartment.getJSONObject("address").getString("locality"));
                                apartment.setInternId(singleApartment.getJSONObject("address").getString("intern_id"));
                                apartment.setStreetNumber(singleApartment.getJSONObject("address").getString("street_number"));
                                apartment.setRoute(singleApartment.getJSONObject("address").getString("route"));
                                apartment.setName(singleApartment.getJSONObject("details").getString("name"));
                                apartment.setMq(singleApartment.getJSONObject("details").getDouble("mq"));
                                apartment.setCarPlace(singleApartment.getJSONObject("details").getDouble("car_place"));
                                apartment.setFreeRooms(singleApartment.getJSONObject("details").getDouble("free_rooms"));
                                apartment.setCost(singleApartment.getJSONObject("details").getDouble("cost"));
                                apartment.setDescription(singleApartment.getJSONObject("details").getString("description"));
                                apartment.setStatus(singleApartment.getJSONObject("details").getString("status"));
                                apartment.add(apartment);


                                Toast.makeText(getActivity(),
                                        "Inserimento Avvenuto Con Successo", Toast.LENGTH_LONG)
                                        .show();

                                HomeFragment homeFragment = new HomeFragment();
                                ((MaterialNavigationDrawer) getActivity()).setFragment(homeFragment, "Home");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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

            private void showDialog() {
                if (!pDialog.isShowing())
                    pDialog.show();
            }

            private void hideDialog() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }




        });
        return view;
    }

    }

