package com.example.onlineshopping.UserFunctions;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.widget.Toast;
public class MyLocationListener implements LocationListener {
    private Context activityContext;

    public MyLocationListener(Context cont) {
        activityContext = cont;
    }

    @Override
    public void onProviderDisabled(String arg0) {
        Toast.makeText(activityContext, " GPS Disabled ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String arg0) {
        Toast.makeText(activityContext, " GPS Enabled ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(activityContext, location.getLatitude() + " , " + location.getLongitude(),
                Toast.LENGTH_LONG).show();
    }
}
