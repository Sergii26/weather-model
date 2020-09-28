package com.practice.weathermodel.receiver;

import io.reactivex.Observable;

public interface NetworkReceiver {
    Observable<Integer> getConnectivityChangeObservable();
}
