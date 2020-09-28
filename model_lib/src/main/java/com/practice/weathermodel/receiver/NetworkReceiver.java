package com.practice.weathermodel.receiver;

import android.content.BroadcastReceiver;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class NetworkReceiver extends BroadcastReceiver {
    protected final PublishSubject<Integer> connectivityChangeObservable = PublishSubject.create();

    public Observable<Integer> getConnectivityChangeObservable() {
        return connectivityChangeObservable;
    }
}
