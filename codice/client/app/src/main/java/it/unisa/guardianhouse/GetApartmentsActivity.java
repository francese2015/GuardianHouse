package it.unisa.guardianhouse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.unisa.guardianhouse.adapter.ApartmentListAdapter;
import it.unisa.guardianhouse.model.Apartment;

public class GetApartmentsActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();
    private ListView listView;
    ApartmentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_apartments);

        // setto la toolbar come action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(this, apartmentList);
        listView.setAdapter(adapter);

        try {
            // json response del server
            String stringJsonApartmentList = "{\n" +
                    "    \"apartments\": [\n" +
                    "        {\n" +
                    "            \"_id\": {\n" +
                    "                \"$oid\": \"5538b19fe4b07a8290702638\"\n" +
                    "            },\n" +
                    "            \"address\": {\n" +
                    "                \"street_number\": \"6\",\n" +
                    "                \"route\": \"Via Roma\",\n" +
                    "                \"locality\": \"Campobasso\",\n" +
                    "                \"administrative_area_level_1\": \"Molise\",\n" +
                    "                \"administrative_area_level_2\": \"Campobasso\",\n" +
                    "                \"postal_code\": \"86100\",\n" +
                    "                \"country\": \"Italia\",\n" +
                    "                \"intern_id\": \"15\"\n" +
                    "            },\n" +
                    "            \"location\": {\n" +
                    "                \"type\": \"Point\",\n" +
                    "                \"coordinates\": [\n" +
                    "                    14.65848,\n" +
                    "                    41.560344\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            \"details\": {\n" +
                    "                \"mq\": 50,\n" +
                    "                \"car_place\": 1,\n" +
                    "                \"contract_time\": \"un anno\",\n" +
                    "                \"free_rooms\": 2,\n" +
                    "                \"cost\": 300,\n" +
                    "                \"status\": \"restaurata\",\n" +
                    "                \"description\": \"appartamento in zona centro\"\n" +
                    "            },\n" +
                    "            \"added_by\": [\n" +
                    "                {\n" +
                    "                    \"user_id\": \"5538b29ca4832c04cba1586b\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"rooms\": [\n" +
                    "                {\n" +
                    "                    \"room_id\": \"1\",\n" +
                    "                    \"beds\": 1,\n" +
                    "                    \"pvt_wc\": 1,\n" +
                    "                    \"windows\": 1,\n" +
                    "                    \"balcony\": 1,\n" +
                    "                    \"sockets\": 4\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"room_id\": \"2\",\n" +
                    "                    \"beds\": 2,\n" +
                    "                    \"pvt_wc\": 1,\n" +
                    "                    \"windows\": 2,\n" +
                    "                    \"balcony\": 1,\n" +
                    "                    \"sockets\": 4\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"receivedReviews\": [\n" +
                    "                {\n" +
                    "                    \"_id\": {\n" +
                    "                        \"$oid\": \"5538b1e6a4832c04cba15864\"\n" +
                    "                    },\n" +
                    "                    \"appliance_status\": \"testo\",\n" +
                    "                    \"thermic_capacity\": \"testo\",\n" +
                    "                    \"landlord_honesty\": \"testo\",\n" +
                    "                    \"security_level\": \"testo\",\n" +
                    "                    \"bus_connection\": \"testo\",\n" +
                    "                    \"neighbours\": \"testo\",\n" +
                    "                    \"distance_cc\": \"testo\",\n" +
                    "                    \"furniture_quality\": \"testo\",\n" +
                    "                    \"description\": \"testo\",\n" +
                    "                    \"reviews_mean\": 0,\n" +
                    "                    \"releaser_id\": \"5538b29ca4832c04cba15869\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"_id\": {\n" +
                    "                        \"$oid\": \"5538b1e6a4832c04cba15865\"\n" +
                    "                    },\n" +
                    "                    \"appliance_status\": \"testo\",\n" +
                    "                    \"thermic_capacity\": \"testo\",\n" +
                    "                    \"landlord_honesty\": \"testo\",\n" +
                    "                    \"security_level\": \"testo\",\n" +
                    "                    \"bus_connection\": \"testo\",\n" +
                    "                    \"neighbours\": \"testo\",\n" +
                    "                    \"distance_cc\": \"testo\",\n" +
                    "                    \"furniture_quality\": \"testo\",\n" +
                    "                    \"description\": \"testo\",\n" +
                    "                    \"reviews_mean\": 0,\n" +
                    "                    \"releaser_id\": \"5538b29ca4832c04cba1586a\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            JSONObject jsonApartmentList = new JSONObject(stringJsonApartmentList);

            // estraggo l'array apartments dal response del server
            JSONArray apartmentArray = jsonApartmentList.getJSONArray("apartments");
            for (int i = 0; i < apartmentArray.length(); i++) {
                JSONObject singleApartment = apartmentArray.getJSONObject(i);
                Apartment apartment = new Apartment();
                apartment.setDescription(singleApartment.getJSONObject("details").getString("description"));
                apartmentList.add(apartment);
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
