package com.androidavanzado.capitalsocial.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.androidavanzado.capitalsocial.R;
import com.androidavanzado.capitalsocial.common.Functions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private String TAG = "Maps";
    private Double latitude;
    private Double longitude;
    private Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        functions.centerTitle(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        requestPermission();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void configLocation(){
        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //return;
        }

        client.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    Log.d(TAG, "onSuccess: Latitude: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: Longitude: " + location.getLongitude());
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    // Add a marker in Sydney and move the camera
                    LatLng myLocation = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("Your Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        configLocation();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            //return the activity
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
