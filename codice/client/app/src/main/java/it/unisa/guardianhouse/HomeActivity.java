package it.unisa.guardianhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private Button btnAccount;
    private Button btnGetUsers;
    private Button btnLogout;
    private Button btnApartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // setto la toolbar come action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        btnAccount = (Button) findViewById(R.id.button_account);
        btnGetUsers = (Button) findViewById(R.id.button_get_users);
        btnLogout = (Button) findViewById(R.id.button_logout);
        btnApartment = (Button) findViewById(R.id.button_apartment);

        // bottone account
        btnAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        // bottone get users
        btnGetUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetUsersActivity.class);
                startActivity(intent);
            }
        });

// bottone apartment

        btnApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ApartmentActivity.class);
                startActivity(intent);

                // force su ApartmentActivity per non entrare in conflitto con
                // gli altri
                // startActivity(new Intent(
                //       "android.intent.action.ApartmentActivity"));

            }
        });

        // bottone logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
