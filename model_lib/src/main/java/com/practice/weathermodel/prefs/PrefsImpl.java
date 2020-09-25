package com.practice.weathermodel.prefs;

import android.content.Context;

public class PrefsImpl extends BasicPrefsImpl implements Prefs {

    private static final String KEY_COORDINATES = "coordinates";

    public PrefsImpl(Context ctx) {
        super(ctx);
    }

    @Override
    public String getDefaultPrefsFileName() {
        return "forecast";
    }


    @Override
    public void putCoordinates(String coordinates) {
        put(KEY_COORDINATES, coordinates);
    }

    @Override
    public String getCoordinates() {
        return get(KEY_COORDINATES, "");
    }
}

