package com.practice.weathermodel.location_api;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import io.reactivex.subjects.AsyncSubject;

public class LocationClient implements LocationSupplier {

    private final FusedLocationProviderClient fusedLocationClient;
    private final AsyncSubject<Result<Location>> locationsSubject = AsyncSubject.create();
    private final OnFailureListener onFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            locationsSubject.onNext(new Result<Location>(e));
            locationsSubject.onComplete();
        }
    };
    private final OnSuccessListener<Location> onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if (location != null) {
                locationsSubject.onNext(new Result<>(location));
            } else {
                locationsSubject.onNext(new Result<Location>(new Exception("Location must not be null")));
            }
            locationsSubject.onComplete();
        }
    };

    public LocationClient(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }

    public AsyncSubject<Result<Location>> getLastLocationObservable() {
        getLastLocation();
        return locationsSubject;
    }
}
