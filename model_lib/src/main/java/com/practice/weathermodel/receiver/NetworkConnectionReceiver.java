package com.practice.weathermodel.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetworkConnectionReceiver extends NetworkReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            connectivityChangeObservable.onNext(1);
        }
    }

}
