package com.practice.weathermodel.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class NetworkConnectionReceiver extends NetworkReceiver {
    private final PublishSubject<Integer> connectivityChangeObservable = PublishSubject.create();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            connectivityChangeObservable.onNext(1);
        }
    }

    public Observable<Integer> getConnectivityChangeObservable() {
        return connectivityChangeObservable;
    }
}
