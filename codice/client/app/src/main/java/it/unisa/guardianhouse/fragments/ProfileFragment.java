package it.unisa.guardianhouse.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import it.unisa.guardianhouse.helpers.SessionManager;


public class ProfileFragment extends Fragment {

    private static String TAG = ProfileFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    SessionManager session;
    NetworkImageView userPhoto;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TextView topUsernameView;
    RatingBar ratingBar;
    TextView usernameView;
    TextView emailView;
    TextView nameView;
    TextView surnameView;
    TextView birthdateView;
    TextView birthplaceView;
    TextView addressView;
    TextView phoneView;
    TextView roleView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        session = new SessionManager(getActivity());

        url = Config.USERS_URL + "/" + session.getUserId();


        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Caricamento...");
        pDialog.show();

        getUserData();

        topUsernameView = (TextView) view.findViewById(R.id.username);
        ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
        nameView = (TextView) view.findViewById(R.id.name_value);
        surnameView = (TextView) view.findViewById(R.id.surname_value);
        emailView = (TextView) view.findViewById(R.id.email_value);
        usernameView = (TextView) view.findViewById(R.id.username_value);
        birthdateView = (TextView) view.findViewById(R.id.birthdate_value);
        birthplaceView = (TextView) view.findViewById(R.id.birthplace_value);
        addressView = (TextView) view.findViewById(R.id.address_value);
        phoneView = (TextView) view.findViewById(R.id.telephone_value);
        roleView = (TextView) view.findViewById(R.id.role_value);


        // richiamo i vari relative layout
        RelativeLayout nameRelative = (RelativeLayout) view.findViewById(R.id.relative_name);
        RelativeLayout surnameRelative = (RelativeLayout) view.findViewById(R.id.relative_surname);
        RelativeLayout emailRelative = (RelativeLayout) view.findViewById(R.id.relative_email);
        RelativeLayout usernameRelative = (RelativeLayout) view.findViewById(R.id.relative_username);
        RelativeLayout passwordRelative = (RelativeLayout) view.findViewById(R.id.relative_password);
        RelativeLayout birthdateRelative = (RelativeLayout) view.findViewById(R.id.relative_birthdate);
        RelativeLayout birthplaceRelative = (RelativeLayout) view.findViewById(R.id.relative_birthplace);
        RelativeLayout addressRelative = (RelativeLayout) view.findViewById(R.id.relative_address);
        RelativeLayout phoneRelative = (RelativeLayout) view.findViewById(R.id.relative_phone);
        RelativeLayout accountTypeRelative = (RelativeLayout) view.findViewById(R.id.relative_account);
        Button btnDeleteAccount = (Button) view.findViewById(R.id.button_delete_account);

        nameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica nome";
                String hint = "Inserisci il nuovo nome";
                showInputDialog(title, hint);
            }
        });

        surnameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica cognome";
                String hint = "Inserisci il nuovo cognome";
                showInputDialog(title, hint);
            }
        });

        emailRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica indirizzo email";
                String hint = "Inserisci il nuovo indirizzo email";
                showInputDialog(title, hint);
            }
        });

        usernameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica username";
                String hint = "Inserisci il nuovo username";
                showInputDialog(title, hint);
            }
        });

        passwordRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica password";
                String hint = "Inserisci la nuova password";
                showInputDialog(title, hint);
            }
        });

        birthdateRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica data di nascita";
                String hint = "Inserisci la nuova data di nascita";
                showInputDialog(title, hint);
            }
        });

        birthplaceRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica luogo di nascita";
                String hint = "Inserisci il nuovo luogo di nascita";
                showInputDialog(title, hint);
            }
        });

        addressRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica indirizzo";
                String hint = "Inserisci il nuovo indirizzo";
                showInputDialog(title, hint);
            }
        });

        phoneRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica telefono";
                String hint = "Inserisci il nuovo telefono";
                showInputDialog(title, hint);
            }
        });

        accountTypeRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // per ora non fa nulla
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });

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
        alertDialogBuilder.setTitle("Elimina account");

        // set dialog message
        alertDialogBuilder
                .setMessage("Sei sicuro di voler eliminare l'account?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity

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

    public void getUserData() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try {
                    JSONObject userJson = response.getJSONObject("user");
                    if (imageLoader == null)
                        imageLoader = AppController.getInstance().getImageLoader();
                    //userPhoto.setImageUrl(userJson.getJSONObject("profile_pic").getString("url"), imageLoader);
                    topUsernameView.setText(userJson.getString("username"));
                    String stringRating = userJson.getString("average_rating");
                    ratingBar.setRating(Float.parseFloat(stringRating));
                    nameView.setText(userJson.getString("name"));
                    surnameView.setText(userJson.getString("surname"));
                    emailView.setText(userJson.getString("email"));
                    usernameView.setText(userJson.getString("username"));
                    birthdateView.setText(userJson.getString("birthdate"));
                    birthplaceView.setText(userJson.getString("birthplace"));
                    addressView.setText(userJson.getString("address"));
                    phoneView.setText(userJson.getString("phone"));
                    roleView.setText(userJson.getString("role"));

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
                headers.put("Authorization", session.getApiKey());
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
