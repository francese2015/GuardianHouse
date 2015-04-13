package it.unisa.guardianhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPassword;
    String username;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // setto la toolbar come action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        inputEmail = (EditText) findViewById(R.id.editText1);
        inputUsername = (EditText) findViewById(R.id.editText2);
        inputPassword = (EditText) findViewById(R.id.editText3);
        btnRegister = (Button) findViewById(R.id.button1);
        btnLinkToLogin = (Button) findViewById(R.id.button2);

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
