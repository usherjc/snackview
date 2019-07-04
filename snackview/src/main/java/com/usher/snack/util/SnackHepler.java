package com.usher.snack.util;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class SnackHepler {

    private static SnackHepler mSnackHepler;

    public static synchronized SnackHepler netInstance() {
        synchronized (SnackHepler.class) {
            if (mSnackHepler == null) {
                mSnackHepler = new SnackHepler();
            }
            return mSnackHepler;
        }
    }

    public void initConfigs(Context context) {
        Fresco.initialize(context);
    }

}
