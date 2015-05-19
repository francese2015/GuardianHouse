package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.NavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.helpers.SQLiteHandler;
import it.unisa.guardianhouse.helpers.SessionManager;
import it.unisa.guardianhouse.utils.MyJsonObjectRequest;


public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    SQLiteHandler db;
    String userId;
    String apiKey;
    EditText inputEmail;
    EditText inputPassword;
    //Button btnLogin;
    ButtonRectangle btnLogin;
    //Button btnLinkToRegister;
    ButtonFlat btnLinkToRegister;
    ProgressDialog pDialog;
    SessionManager session;
    String email;
    String password;
    Map<String, String> params;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        inputEmail = (EditText) view.findViewById(R.id.editText1);
        inputPassword = (EditText) view.findViewById(R.id.editText2);
        btnLogin = (ButtonRectangle) view.findViewById(R.id.button1);
        btnLinkToRegister = (ButtonFlat) view.findViewById(R.id.button2);

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getActivity());

        db = new SQLiteHandler(getActivity());

        // bottone login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                // controllo se sono stati inseriti dati nel form
                if (email.trim().length() > 0 && password.trim().length() > 0) {

                    params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);

                    makeRequest();

                } else {
                    // chiedo all'utente di inserire i dati
                    Toast.makeText(getActivity(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // link alla schermata di registrazione
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragment(registerFragment, "Registrazione");
            }
        });

        return view;
    }

    private void makeRequest() {
        // Tag used to cancel the request
        String tag_jObj_req = "req_login";

        pDialog.setMessage("Accesso in corso ...");
        showDialog();

        MyJsonObjectRequest strReq = new MyJsonObjectRequest(Request.Method.POST,
                Config.LOGIN_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    Boolean error = response.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        userId = response.getJSONObject("user").getJSONObject("id").getString("$id");
                        apiKey = response.getJSONObject("user").getString("api_key");
                        session.setLogin(true, apiKey);

                        Toast.makeText(getActivity(),
                                "Login effettuato! :)", Toast.LENGTH_LONG).show();
                        ((NavigationDrawer) getActivity()).loginUser();
                        HomeFragment homeFragment = new HomeFragment();
                        ((MaterialNavigationDrawer) getActivity()).setFragment(homeFragment, "Home");

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = response.getString("error_msg");
                        Toast.makeText(getActivity(),
                                errorMsg, Toast.LENGTH_LONG).show();
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
                hideDialog();
            }
        }) {

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_jObj_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}