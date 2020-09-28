package com.practice.weathermodel.location_api;

import android.location.Location;

import io.reactivex.Observable;

public interface LocationSupplier {
    Observable<Result<Location>> getLastLocationObservable();
}
