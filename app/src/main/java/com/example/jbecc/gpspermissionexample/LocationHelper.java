package com.example.jbecc.gpspermissionexample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

public class LocationHelper extends Activity implements LocationListener {

    final private int REQUEST_CODE_GPS_PERMISSIONS = 1;
    private TextView latitude;
    private TextView longitude;
    private Context context;

    public LocationHelper(TextView latitude, TextView longitude, Context context) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.context = context;
    }

    public void refreshLocation() {
        Location location = getLocation();

        if (location != null) {
            latitude.setText(String.format("Latitude: %s", location.getLatitude()));
            longitude.setText(String.format("Longitude: %s", location.getLongitude()));
        }
    }

    private Location getLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, this);

            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);

            return locationManager.getLastKnownLocation(provider);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_GPS_PERMISSIONS);

            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_GPS_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    refreshLocation();
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
