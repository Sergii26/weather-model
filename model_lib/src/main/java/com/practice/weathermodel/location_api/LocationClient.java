package com.practice.weathermodel.location_api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import io.reactivex.subjects.AsyncSubject;

public class LocationClient implements LocationSupplier {

    private final FusedLocationProviderClient fusedLocationClient;
    private final AsyncSubject<Location> locationsSubject = AsyncSubject.create();
    private final OnSuccessListener<Location> listener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if(location != null){
                locationsSubject.onNext(location);
                locationsSubject.onComplete();
            } else {
                locationsSubject.onError(new IllegalStateException("Location must not be null"));
            }
        }
    };

    public LocationClient(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(listener);
    }

    public AsyncSubject<Location> getLastLocationObservable() {
        getLastLocation();
        return locationsSubject;
    }
}
