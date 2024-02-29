package com.example.onlineshopping.UserFunctions;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineshopping.R;
import com.example.onlineshopping.UserFunctions.MyLocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //private ActivityMapsBinding binding;
    EditText addressText;
    LocationManager locManager;
    MyLocationListener locListener;
    Button getLocation;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        addressText = (EditText) findViewById(R.id.editText);


        Button backpressed = (Button)findViewById(R.id.backpage);
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(), Categories_Activity.class);
                startActivity(inte);
            }
        });

        //binding = ActivityMapsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.

        getLocation = (Button) findViewById(R.id.btn1);
        locListener = new MyLocationListener(getApplicationContext());
        locManager = (LocationManager) getSystemService((Context.LOCATION_SERVICE));

        try {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 5, locListener);
        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), "You are not allowed to access the current location",
                    Toast.LENGTH_SHORT).show();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(@NonNull com.google.android.gms.maps.GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960, 31.235711600), 8));
        getLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                mMap.clear();
                Geocoder coder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                Location loc = null;
                try {
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } catch (SecurityException x) {
                    Toast.makeText(getApplicationContext(), " You did not allow to access the current location ",
                            Toast.LENGTH_LONG).show();
                }
                if (loc != null) {
                    LatLng myPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
                    try {
                        addressList = coder.getFromLocation(myPosition.latitude, myPosition.longitude, 1);

                        if (!addressList.isEmpty()) {
                            String address = " ";
                            for (int i = 0; i <= addressList.get(0).getMaxAddressLineIndex(); i++)
                                address += addressList.get(0).getAddressLine(i) + " , ";

                            mMap.addMarker(new MarkerOptions().position(myPosition).title("My location ").snippet(address)).setDraggable(true);
                            addressText.setText(address);
                        }
                    } catch (IOException e) {
                        // if network not available
                        mMap.addMarker(new MarkerOptions().position(myPosition).title("My location"));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
                } else
                    Toast.makeText(getApplicationContext(), "Please wait until your position is determined", Toast.LENGTH_LONG).show();
            }
        });
    }
}