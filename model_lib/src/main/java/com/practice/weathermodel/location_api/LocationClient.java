package com.practice.weathermodel.location_api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import io.reactivex.subjects.AsyncSubject;

public class LocationClient implements LocationSupplier {

    private final FusedLocationProviderClient fusedLocationClient;
    private final AsyncSubject<Result> locationsSubject = AsyncSubject.create();
    private final boolean hasPermission;
    private final OnFailureListener onFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            locationsSubject.onNext(new Result<Exception>(e));
            locationsSubject.onComplete();
        }
    };
    private final OnSuccessListener<Location> onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if(location != null){
                locationsSubject.onNext(new Result<Location>(location));
                locationsSubject.onComplete();
            } else {
                locationsSubject.onNext(new Result<Exception>(new Exception("Location must not be null")));
                locationsSubject.onComplete();
            }
        }
    };

    public LocationClient(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        hasPermission = ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }

    public AsyncSubject<Result> getLastLocationObservable() {

        if(hasPermission) {
            getLastLocation();
        } else {
            locationsSubject.onNext(new Result(new Exception("No permission")));
        }
        return locationsSubject;
    }
}
