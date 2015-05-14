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
public class RegisterFragment extends Fragment {

    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPassword;
    String username;
    String email;
    String password;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        inputEmail = (EditText) view.findViewById(R.id.editText1);
        inputUsername = (EditText) view.findViewById(R.id.editText2);
        inputPassword = (EditText) view.findViewById(R.id.editText3);
        btnRegister = (Button) view.findViewById(R.id.button1);
        btnLinkToLogin = (Button) view.findViewById(R.id.button2);

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

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
                Intent i = new Intent(getActivity(),
                        LoginFragment.class);
                startActivity(i);
                //finish();
            }
        });

        return view;
    }


}
