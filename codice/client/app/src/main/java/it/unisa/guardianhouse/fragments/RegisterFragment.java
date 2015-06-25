package it.unisa.guardianhouse.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.helpers.SQLiteHandler;
import it.unisa.guardianhouse.helpers.SessionManager;
import it.unisa.guardianhouse.utils.MyJsonObjectRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = RegisterFragment.class.getSimpleName();
    private ButtonRectangle btnRegister;
    private ButtonFlat btnLinkToLogin;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText repeatPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    String username;
    String email;
    String password;
    String repeatpass;
    Map<String, String> params;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        setHasOptionsMenu(true);

        inputEmail = (EditText) view.findViewById(R.id.editText1);
        inputUsername = (EditText) view.findViewById(R.id.editText2);
        inputPassword = (EditText) view.findViewById(R.id.editText3);
        repeatPassword = (EditText) view.findViewById(R.id.editText4);
        btnRegister = (ButtonRectangle) view.findViewById(R.id.button1);
        btnLinkToLogin = (ButtonFlat) view.findViewById(R.id.button2);

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        session = new SessionManager(getActivity());

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                repeatpass = repeatPassword.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !repeatpass.isEmpty()) {



                    if(repeatpass.equals(password)){

                        params = new HashMap<String, String>();
                        params.put("username", username);
                        params.put("password", password);
                        params.put("email", email);

                        registerUser();

                    } else {

                        Toast.makeText(getActivity(),
                                "Ripeti correttamente la password", Toast.LENGTH_LONG)
                                .show();

                    }

                } else {
                    Toast.makeText(getActivity(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }



            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragment(loginFragment, "Login");
            }
        });

        return view;
    }

    private void registerUser() {
        // Tag used to cancel the request
        String tag_jObj_req = "req_register";

        pDialog.setMessage("Registrazione in corso...");
        showDialog();

        MyJsonObjectRequest strReq = new MyJsonObjectRequest(Request.Method.POST,
                Config.REGISTER_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    Boolean error = response.getBoolean("error");
                    if (!error) {
                        Toast.makeText(getActivity(),
                                "Registrazione avvenuta con successo!", Toast.LENGTH_LONG)
                                .show();
                        LoginFragment loginFragment = new LoginFragment();
                        ((MaterialNavigationDrawer) getActivity()).setFragment(loginFragment, "Login");

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = response.getString("error_msg");
                        Toast.makeText(getActivity(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                SearchFragment searchFragment = new SearchFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(searchFragment, "Cerca appartamento");
                return true;
            default:
                break;
        }
        return false;
    }


}
