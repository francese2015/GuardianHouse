package it.unisa.guardianhouse.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;


public class ApartmentFragment extends Fragment {

    private static String TAG = ApartmentFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    NetworkImageView thumbnail;
    TextView nameApt;
    String aptId;
    RatingBar ratingBar;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ApartmentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apartment, container, false);

        Bundle b = getArguments();
        aptId = b.getString("aptId");
        url = Config.APARTMENTS_URL + "/" + aptId;

        nameApt = (TextView) view.findViewById(R.id.name_apt);
        ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
        thumbnail = (NetworkImageView)view.findViewById(R.id.thumbnailApt);

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

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Ricerca in corso...");
        pDialog.show();

        getApartmentData();

        return view;
    }

    protected void showInputDialog(String title, String hint) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
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

    public void getApartmentData() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try {
                    JSONObject singleApartment = response.getJSONObject("apartment");
                    if (imageLoader == null)
                        imageLoader = AppController.getInstance().getImageLoader();
                    thumbnail.setImageUrl(singleApartment.getJSONArray("pictures").getJSONObject(0).getString("url"), imageLoader);
                    //ottengo il nome appartamento
                    nameApt.setText(singleApartment.getJSONObject("details").getString("name"));
                    //ottengo il rating
                    String stringRating = singleApartment.getString("average_rating");
                    ratingBar.setRating(Float.parseFloat(stringRating));
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

}
