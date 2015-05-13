package it.unisa.guardianhouse.fragments.activities.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.ApartmentListAdapter;
import it.unisa.guardianhouse.app.AppController;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.models.Apartment;

public class GetApartmentsActivity extends ActionBarActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String url;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();
    private ListView listView;
    ApartmentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_apartments);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ApartmentListAdapter(this, apartmentList);
        listView.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        double latitude = b.getDouble("latitude");
        double longitude = b.getDouble("longitude");
        int distance = b.getInt("distance");

        url = Config.SEARCH_APT_URL + "/" + latitude + "," + longitude + "," + distance;

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Ricerca in corso...");
        pDialog.show();

        searchByLocation();

    }

    public void searchByLocation() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
                (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hidePDialog();
                        try {
                            JSONArray apartmentArray = response.getJSONArray("result");
                            for (int i = 0; i < apartmentArray.length(); i++) {
                                JSONObject singleApartment = apartmentArray.getJSONObject(i);
                                Apartment apartment = new Apartment();
                                apartment.setDescription(singleApartment.getJSONObject("details").getString("name"));
                                apartmentList.add(apartment);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
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
