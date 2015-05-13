package it.unisa.guardianhouse.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;

import it.unisa.guardianhouse.*;
import it.unisa.guardianhouse.R;


public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(it.unisa.guardianhouse.R.layout.activity_map);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        double latitude = 41.560344;
        double longitude = 14.65848;

        LatLng apartment = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(apartment, 13));

        map.addMarker(new MarkerOptions()
                .title("Nome appartamento")
                .snippet("Descrizione.")
                .position(apartment));
    }
}