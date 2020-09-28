package com.practice.weathermodel.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetworkStatusChangeReceiver extends NetworkReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            statusChangeObservable.onNext(1);
        }
    }

}
