package com.practice.weathermodel.receiver;

import android.content.BroadcastReceiver;

import io.reactivex.Observable;

public abstract class NetworkReceiver extends BroadcastReceiver {
    abstract Observable<Integer> getConnectivityChangeObservable();
}
