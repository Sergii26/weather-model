package com.practice.weathermodel.location_api;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class LocationClient implements LocationSupplier {
    private final LocationManager locationManager;
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
            location = null;
            if (location != null) {
                getLastLocationByLocationManager();
            } else {
                locationsSubject.onNext(new Result<Location>(new Exception("Location must not be null")));
                locationsSubject.onComplete();
            }

        }
    };
    private final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("LOGG", "onLocationChanged " + location);
            locationsSubject.onNext(new Result<>(location));
            locationsSubject.onComplete();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LOGG", "onStatusChanged " + provider + ", sts " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("LOGG", "onProviderEnabled " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("LOGG", "onProviderDisabled " + provider);
        }
    };

    public LocationClient(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocationByLocationManager() {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, listener, Looper.myLooper());
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, listener, Looper.myLooper());
        locationManager.requestSingleUpdate(LocationManager.PASSIVE_PROVIDER, listener, Looper.myLooper());
    }

    public Observable<Result<Location>> getLastLocationObservable() {
        getLastLocation();
        return locationsSubject;
    }



}
