package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.models.Apartment;


public class ResultsMapFragment extends Fragment {

    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;
    private Double latitude;
    private Double longitude;
    private int distance;
    private List<Apartment> apartmentList = new ArrayList<Apartment>();

    public ResultsMapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results_map, container, false);

        MapsInitializer.initialize(getActivity());

        Bundle b = getArguments();
        apartmentList = b.getParcelableArrayList("aptData");
        latitude = b.getDouble("myLatitude");
        longitude = b.getDouble("myLongitude");
        distance = b.getInt("distance");

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(mBundle);

        setUpMapIfNeeded(view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = savedInstanceState;
    }

    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        for (int i = 0; i < apartmentList.size(); i++) {
            double lat = apartmentList.get(i).getLatitude();
            double lng = apartmentList.get(i).getLongitude();
            LatLng point = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(point).title(apartmentList.get(i).getName()));
        }
        LatLng center = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

}