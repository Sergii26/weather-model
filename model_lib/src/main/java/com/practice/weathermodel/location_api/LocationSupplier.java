package com.practice.weathermodel.location_api;

import android.location.Location;

import io.reactivex.subjects.AsyncSubject;

public interface LocationSupplier {
    AsyncSubject<Result> getLastLocationObservable();
}
