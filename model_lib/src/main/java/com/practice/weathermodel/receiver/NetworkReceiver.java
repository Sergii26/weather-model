package com.practice.weathermodel.receiver;

import android.content.BroadcastReceiver;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class NetworkReceiver extends BroadcastReceiver {
    protected final PublishSubject<Integer> statusChangeObservable = PublishSubject.create();

    public Observable<Integer> getStatusChangeObservable() {
        return statusChangeObservable;
    }
}
