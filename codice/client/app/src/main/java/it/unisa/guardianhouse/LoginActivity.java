package it.unisa.guardianhouse;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private Toolbar toolbar;
    EditText inputEmail;
    EditText inputPassword;
    Button btnLogin;
    Button btnLinkToRegister;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // setto la toolbar come action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        inputEmail = (EditText) findViewById(R.id.editText1);
        inputPassword = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.button1);
        btnLinkToRegister = (Button) findViewById(R.id.button2);

        // bottone login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                // controllo se sono stati inseriti dati nel form
                if (email.trim().length() > 0 && password.trim().length() > 0) {

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                } else {
                    // chiedo all'utente di inserire i dati
                    Toast.makeText(getApplicationContext(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // link alla schermata di registrazione
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
