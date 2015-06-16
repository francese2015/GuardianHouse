package it.unisa.guardianhouse.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
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
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Review;


public class ApartmentFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private static String TAG = ApartmentFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;
    private Double latitude;
    private Double longitude;
    Double distance;
    private String aptId;
    private ImageView featured;
    private SliderLayout imgSlider;
    private TextView nameApt;
    TextView username;
    String userId;
    TextView description;
    TextView mq;
    TextView car_place;
    TextView contract_time;
    TextView free_rooms;
    TextView cost;
    TextView status;
    TextView addressComplete;
    private RatingBar ratingBar;
    private NetworkImageView thumbnail;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private Button btnShowReview;
    private Button btnInsReview;
    private ArrayList<Review> reviewList = new ArrayList<Review>();
    // private TextView distanceTextView;



    Bundle bundle;

    public ApartmentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apartment, container, false);
        setHasOptionsMenu(true);

        MapsInitializer.initialize(getActivity());

        Bundle b = getArguments();
        aptId = b.getString("aptId");
        //latitude = b.getDouble("itemLatitude");
        //longitude = b.getDouble("itemLongitude");
        distance = b.getDouble("distance");
        url = Config.APARTMENTS_URL + "/" + aptId;

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Caricamento...");
        pDialog.show();



        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(mBundle);


        // codice image slider
        imgSlider = (SliderLayout) view.findViewById(R.id.img_slider);
        imgSlider.stopAutoCycle();
        imgSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        imgSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imgSlider.setDuration(4000);
        imgSlider.addOnPageChangeListener(this);

        featured = (ImageView) view.findViewById(R.id.imgViewFeatured);
        nameApt = (TextView) view.findViewById(R.id.name_apt);
        addressComplete = (TextView) view.findViewById(R.id.address_value);
        description = (TextView) view.findViewById(R.id.text_value);
        mq = (TextView) view.findViewById(R.id.dimension_value);
        car_place = (TextView) view.findViewById(R.id.car_place_value);
        contract_time = (TextView) view.findViewById(R.id.contract_time_value);
        free_rooms = (TextView) view.findViewById(R.id.free_rooms_value);
        cost = (TextView) view.findViewById(R.id.price_value);
        status = (TextView) view.findViewById(R.id.status_value);
        username = (TextView) view.findViewById(R.id.inserted_by_value);
        ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
        thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnailApt);
        btnShowReview = (Button) view.findViewById(R.id.button_view_reviews);
        btnInsReview = (Button) view.findViewById(R.id.button_ins_review);
        //distanceTextView = (TextView) view.findViewById(R.id.distanceFromLocation);
        //DecimalFormat precision = new DecimalFormat("##.##");
        //distanceTextView.setText("Distanza: " + precision.format(distance) + " Km");

        getApartmentData(view);


        // Link to Login Screen
        btnShowReview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ReviewListFragment reviewListFragment = new ReviewListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("myAptId", aptId);
                bundle.putString("user_id",userId);
                reviewListFragment.setArguments(bundle);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(reviewListFragment, "Lista recensioni");
            }
        });

        //Insert Review
        btnInsReview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                InsertReviewFragment insertReviewFragment = new InsertReviewFragment();
                Bundle b = new Bundle();
                b.putString("AptId", aptId);
                insertReviewFragment.setArguments(b);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(insertReviewFragment, "Lascia una Recensione");
            }
        });


        TextView txtUserInsert = (TextView) view.findViewById(R.id.inserted_by_value);

        txtUserInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                FeedbackListFragment feedbackListFragment = new FeedbackListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("user_id", userId); //passo l'id della persona a cui si riferisce il feedback
                bundle.putString("username", String.valueOf(username));
                feedbackListFragment.setArguments(bundle);
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(feedbackListFragment, "Lista Feedback");

            }
        });

        /*
        RelativeLayout dimensionRelative = (RelativeLayout) view.findViewById(R.id.relative_dimension);
        RelativeLayout statusRelative = (RelativeLayout) view.findViewById(R.id.relative_status);
        RelativeLayout priceRelative = (RelativeLayout) view.findViewById(R.id.relative_price);
        RelativeLayout descriptionRelative = (RelativeLayout) view.findViewById(R.id.relative_description);
        RelativeLayout positionRelative = (RelativeLayout) view.findViewById(R.id.relative_position);
        RelativeLayout poiRelative = (RelativeLayout) view.findViewById(R.id.relative_poi);
        RelativeLayout thermicRelative = (RelativeLayout) view.findViewById(R.id.relative_thermic);
        RelativeLayout servicesRelative = (RelativeLayout) view.findViewById(R.id.relative_services);
        RelativeLayout testRelative = (RelativeLayout) view.findViewById(R.id.relative_test);
        Button btnDeleteApartment = (Button) view.findViewById(R.id.button_less);

        dimensionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Dimensione";
                String hint = "Inserisci nuove dimensioni";
                showInputDialog(title, hint);
            }
        });

        statusRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Status/Condizioni";
                String hint = "Inserisci nuovo Status/Condizioni";
                showInputDialog(title, hint);
            }
        });

        priceRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica il prezzo mensile";
                String hint = "Inserisci nuovo prezzo";
                showInputDialog(title, hint);
            }
        });

        descriptionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica descrizione";
                String hint = "Inserisci nuova descrizione";
                showInputDialog(title, hint);
            }
        });

        positionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Collegamenti";
                String hint = "Inserisci nuovi Collegamenti";
                showInputDialog(title, hint);
            }
        });

        poiRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Vicinanza ai Punti d'Interesse";
                String hint = "Inserisci Nuovi Punti d'Interesse";
                showInputDialog(title, hint);
            }
        });

        thermicRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Descrizione Capacita' Termica";
                String hint = "Inserisci Nuova Descrizione";
                showInputDialog(title, hint);
            }
        });

        servicesRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Servizi Standard";
                String hint = "Inserisci Nuovi Servizi Standard";
                showInputDialog(title, hint);
            }
        });

        testRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Quanto Puzza?";
                String hint = "Inserisci un valore minimo di 1000";
                showInputDialog(title, hint);
            }
        });



        btnDeleteApartment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        */

        return view;
    }

    protected void showInputDialog(String title, String hint) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setTitle(title);
        final EditText dialogHint = (EditText) promptView.findViewById(R.id.editText);
        dialogHint.setHint(hint);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showAlertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // set title
        alertDialogBuilder.setTitle("Elimina appartamento");

        // set dialog message
        alertDialogBuilder
                .setMessage("Sei sicuro di voler eliminare la scheda appartamento?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        getActivity().finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void getApartmentData(final View view) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                VolleyLog.d(TAG, "Response: " + response);
                try {
                    JSONObject singleApartment = response.getJSONObject("apartment");
                    if (imageLoader == null)
                        imageLoader = AppController.getInstance().getImageLoader();
                    if (singleApartment.getJSONObject("details").getBoolean("featured") == false) {
                        featured.setVisibility(View.INVISIBLE);
                    }
                    //thumbnail.setImageUrl(singleApartment.getJSONArray("pictures").getJSONObject(0).getString("url"), imageLoader);
                    //ottengo il nome appartamento
                    nameApt.setText(singleApartment.getJSONObject("details").getString("name"));
                    userId = singleApartment.getJSONObject("added_by").getString("user_id");
                    username.setText(singleApartment.getJSONObject("added_by").getString("username"));

                    String streetNumberString = singleApartment.getJSONObject("address").getString("street_number");
                    String routeString = singleApartment.getJSONObject("address").getString("route");
                    String localityString = singleApartment.getJSONObject("address").getString("locality");
                    String postalCodeString = singleApartment.getJSONObject("address").getString("postal_code");
                    String countryString = singleApartment.getJSONObject("address").getString("country");
                    String addressString = routeString + " " + streetNumberString + ", " + postalCodeString + " " +
                            localityString + ", " + countryString;

                    addressComplete.setText(addressString);
                    description.setText(singleApartment.getJSONObject("details").getString("description"));
                    mq.setText(singleApartment.getJSONObject("details").getString("mq"));
                    car_place.setText(singleApartment.getJSONObject("details").getString("car_place"));
                    contract_time.setText(singleApartment.getJSONObject("details").getString("contract_time"));
                    free_rooms.setText(singleApartment.getJSONObject("details").getString("free_rooms"));
                    cost.setText(singleApartment.getJSONObject("details").getString("cost"));
                    status.setText(singleApartment.getJSONObject("details").getString("status"));

                    //ottengo il rating
                    String stringRating = singleApartment.getString("average_rating");
                    ratingBar.setRating(Float.parseFloat(stringRating));
                    latitude = singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(1);
                    longitude = singleApartment.getJSONObject("location").getJSONArray("coordinates").getDouble(0);

                    JSONArray photoArray = singleApartment.getJSONArray("pictures");
                    HashMap<String, String> url_maps = new HashMap<String, String>();
                    for (int i = 0; i < photoArray.length(); i++) {
                        url_maps.put("photo " + i, photoArray.getJSONObject(i).getString("url"));
                    }
                    for(String name : url_maps.keySet()){
                        DefaultSliderView sliderView = new DefaultSliderView(getActivity());
                        // initialize a SliderLayout
                        sliderView
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit);
                        imgSlider.addSlider(sliderView);
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
        LatLng point = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(point).title("Appartamento"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
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
        super.onDestroy();
    }

    // altri metodi dello slider
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        imgSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        // aprire immagine qui
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_apartment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_apt:
                // codice per modificare appartamento
                return true;
            case R.id.action_delete_apt:
                // codice per eliminare appartamento
                return true;
            default:
                break;
        }
        return false;
    }

}
