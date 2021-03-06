package com.practice.weathermodel.receiver;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class NetworkReceiver extends BroadcastReceiver {
    protected final PublishSubject<Integer> statusChangeObservable = PublishSubject.create();

    public IntentFilter getIntentFilter(){
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    public Observable<Integer> getStatusChangeObservable() {
        return statusChangeObservable;
    }
}
