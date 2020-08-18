package com.practice.weathermodel.logger;

import android.util.Log;

public class Logger implements ILog {

    private final String TAG;
    private final int priority;

    public static Logger withTag(String tag) {
        return new Logger(tag);
    }

    private Logger(String TAG) {
        this.TAG = TAG;
        this.priority = Log.DEBUG;
    }

    public Logger log(String message) {
            Log.println(priority, TAG, message);
        return this;
    }

    public void withCause(Exception cause) {
            Log.println(priority, TAG, Log.getStackTraceString(cause));
    }

}
