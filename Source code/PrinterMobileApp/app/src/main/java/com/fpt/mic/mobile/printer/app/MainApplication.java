package com.fpt.mic.mobile.printer.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by trungdq on 06/08/2014.
 */
public class MainApplication extends Application {

    public static Context mContext;

    /**
     * Static class to get app context everywhere.
     * Use it carefully if or you will get memory leak
     * @return Context
     */
    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
