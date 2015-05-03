package it.unisa.guardianhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity {

    private Button btnAccount;
    private Button btnGetUsers;
    private Button btnLogout;
    private Button btnApartment;
    private Button btnReview;
    private Button btnSearch;
    private Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnAccount = (Button) findViewById(R.id.button_account);
        btnGetUsers = (Button) findViewById(R.id.button_get_users);
        btnApartment = (Button) findViewById(R.id.button_apartment);
        btnReview = (Button) findViewById(R.id.button_review);
        btnSearch = (Button) findViewById(R.id.button_search);
        btnMap = (Button) findViewById(R.id.button_map);
        btnLogout = (Button) findViewById(R.id.button_logout);

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

        //bottone review
        btnReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GetReviewActivity.class);
                startActivity(intent);

            }
        });

        //bottone search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        //bottone map
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        // bottone logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.finish();
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
