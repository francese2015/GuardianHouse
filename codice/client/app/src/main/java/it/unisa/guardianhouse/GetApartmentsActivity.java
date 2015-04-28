package it.unisa.guardianhouse;

/**
 * Created by Nae on 18.04.2015.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import it.unisa.guardianhouse.adapter.ApartmentListAdapter;
import it.unisa.guardianhouse.model.Apartment;

public class GetApartmentsActivity extends ActionBarActivity{

    private Toolbar toolbar;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();
    private ListView listView;
    ApartmentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // setto la toolbar come action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        listView = (ListView) findViewById(R.id.listView2);
        adapter = new ApartmentListAdapter(this, apartmentList);
        listView.setAdapter(adapter);

        /*try {
            // simulo il response del server
            String stringJsonApartmentList = "{\"users\":[{\"id\":\"37\",\"api_key\":\"bf45c093e542f057caee68c4" +
                    "77dbt7zg\",\"username\":\"m.rossi\",\"encrypted_password\":\"t6lGmFNmZCP9xibSb48" +
                    "xCFGraPc0Y2FkZjbTdfRz\",\"salt\":\"4cadf5f986\",\"email\":\"m.rossi@email.it\"," +
                    "\"name\":\"Mario\",\"surname\":\"Rossi\",\"birthdate\":\"1989-03-02\",\"birthpla" +
                    "ce\":\"Roma\",\"address\":\"Via Garibaldi 12\",\"phone\":\"3397483647\",\"role" +
                    "\":\"admin\"},{\"id\":\"46\",\"api_key\":\"ffb032fb7c073d16a9b5e7146fd6e3zz" +
                    "\",\"username\":\"c.tranzollo\",\"encrypted_password\":\"OexyZAZdn3eNQ2og89Xq01x" +
                    "ufBBjNTgzOWUyzZzZ\",\"salt\":\"c5839e2d1z\",\"email\":\"c.tranzollo@email.it" +
                    "\",\"name\":\"Claudio\",\"surname\":\"Tranzollo\",\"birthdate\":\"1989-06-02\"" +
                    ",\"birthplace\":\"Campobasso\",\"address\":\"Via Colvento 12\",\"phone\":\"33874" +
                    "83647\",\"role\":\"admin\"}]}";
            JSONObject jsonUserList = new JSONObject(stringJsonApartmentList);

            // estraggo l'array users dal response del server
            JSONArray apartmentArray = jsonUserList.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject singleUser = usersArray.getJSONObject(i);
                User user = new User();
                user.setUsername(singleUser.getString("username"));
                user.setName(singleUser.getString("name"));
                user.setSurname(singleUser.getString("surname"));
                userList.add(user);
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_apartments, menu);
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
