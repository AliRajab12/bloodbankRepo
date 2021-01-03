package com.example.ali.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class gpsListener implements LocationListener {
    LocationManager locationManager;
    Location location;
    Context ct;
    gpsListener(Context ct){
        this.ct=ct;
    }
    public Location getLocation(){
        if (ContextCompat.checkSelfPermission(ct, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(ct,"Permission Not granted",Toast.LENGTH_LONG).show();
            return null;
        }
        locationManager=(LocationManager)ct.getSystemService(ct.LOCATION_SERVICE);
        boolean isGPSEnabled=locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        if (isGPSEnabled){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 10, 0, this);
            location=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            if (location == null){
                location=locationManager.getLastKnownLocation(locationManager.PASSIVE_PROVIDER);}
            return location;
        }else {
            Toast.makeText(ct,"Please Enable GPS",Toast.LENGTH_LONG).show();
        }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
