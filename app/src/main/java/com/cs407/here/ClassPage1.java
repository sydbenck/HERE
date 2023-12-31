package com.cs407.here;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ClassPage1 extends AppCompatActivity {

    private LocationRequest locationRequest;
    private Button hereButton;
    double longitude;
    double latitude;
    TextView classText;
    ClassList classList = new ClassList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page1);
        classText=(TextView) findViewById(R.id.classInfoText);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        classText.setText(str);

        hereButton = findViewById(R.id.hereButton);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);


        hereButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(ClassPage1.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(isGPSEnabled()) {
                            LocationServices.getFusedLocationProviderClient(ClassPage1.this)
                                    .requestLocationUpdates(locationRequest, new LocationCallback() {
                                        @Override
                                        public void onLocationResult(@NonNull LocationResult locationResult) {
                                            super.onLocationResult(locationResult);

                                            LocationServices.getFusedLocationProviderClient(ClassPage1.this)
                                                    .removeLocationUpdates(this);
                                            if(locationResult != null && locationResult.getLocations().size() > 0){
                                                int index = locationResult.getLocations().size() - 1;
                                                latitude = locationResult.getLocations().get(index).getLatitude();
                                                longitude = locationResult.getLocations().get(index).getLongitude();

                                                Log.d("TAG", "Latitude: " + latitude);
                                                Log.d("TAG", "Longitude: " + longitude);
                                                Log.d("TAG",  "" + classList.cs);
                                                Log.d("TAG",  "" + classList.art);
                                                if(classList.art == true){
                                                    if(latitude <= 43.073392 && latitude >= 43.073374 && longitude <= -89.406075 && longitude >= -89.399023){
                                                        goToCheckin();
                                                    } else {
                                                        Toast.makeText(ClassPage1.this, "Sorry not in range!", Toast.LENGTH_SHORT).show();
                                                        Log.d("TAG", "Not here ");
                                                    }
                                                }else if(classList.cs == true){
                                                    Log.d("TAG", " " + (latitude <= 43.071922) + (latitude >= 43.071146) + (longitude <= -89.407246) + (longitude >= -89.406075));
                                                    if(latitude <= 43.071922 && latitude >= 43.071146 && longitude >= -89.407246 && longitude <= -89.406075){

                                                        goToCheckin();
                                                    } else {
                                                        Toast.makeText(ClassPage1.this, "Sorry not in range!", Toast.LENGTH_SHORT).show();
                                                        Log.d("TAG", "Not here ");
                                                    }
                                                }
                                            }
                                        }
                                    }, Looper.getMainLooper());
                        }else{
                            turnOnGPS();
                        }
                    }else{
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                }
            }
        });
    }





    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(ClassPage1.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(ClassPage1.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    public void goToCheckin(){
        Intent intent = new Intent(this, CheckIn.class);
        startActivity(intent);
    }

    private boolean isGPSEnabled(){
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if(locationManager == null){
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    public void goToCheckIn(View view){
        Intent intent = new Intent(this, CheckIn.class);
        startActivity(intent);
    }

    public void goToAbsent(View view){
        Intent intent = new Intent(this, Absent.class);
        startActivity(intent);
    }

}