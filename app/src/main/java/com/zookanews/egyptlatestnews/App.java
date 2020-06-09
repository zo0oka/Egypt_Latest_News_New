package com.zookanews.egyptlatestnews;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.zookanews.egyptlatestnews.db.AppDB;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
public class App extends Application {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppDB db = AppDB.getDatabase(this);
    }
}
