package it.unisa.guardianhouse.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.unisa.guardianhouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText inputEmail;
    EditText inputPassword;
    Button btnLogin;
    Button btnLinkToRegister;
    String email;
    String password;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        inputEmail = (EditText) view.findViewById(R.id.editText1);
        inputPassword = (EditText) view.findViewById(R.id.editText2);
        btnLogin = (Button) view.findViewById(R.id.button1);
        btnLinkToRegister = (Button) view.findViewById(R.id.button2);

        // bottone login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                // controllo se sono stati inseriti dati nel form
                if (email.trim().length() > 0 && password.trim().length() > 0) {

                    //Intent intent = new Intent(getActivity(), HomeActivity.class);
                    //startActivity(intent);

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
                Intent i = new Intent(getActivity(),
                        RegisterFragment.class);
                startActivity(i);
                //finish();
            }
        });

        return view;
    }


}
