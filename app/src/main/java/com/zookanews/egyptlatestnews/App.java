package com.zookanews.egyptlatestnews;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.zookanews.egyptlatestnews.helper.AdsHelper;
import com.zookanews.egyptlatestnews.helper.LanguageManager;
import com.zookanews.egyptlatestnews.helper.PreferencesManager;
import com.zookanews.egyptlatestnews.worker.DbCleanUpWorker;
import com.zookanews.egyptlatestnews.worker.DbSyncWorker;

import java.util.concurrent.TimeUnit;

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

        PreferencesManager.init(this);
        LanguageManager.init(this);
        AdsHelper.initAds(this);
        initWorkManager();
    }

    private void initWorkManager() {
        WorkManager workManager = WorkManager.getInstance(this);
        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(DbSyncWorker.class,
                Integer.parseInt(PreferencesManager.getString(PreferencesManager.SYNC_FREQUENCY)), TimeUnit.MINUTES);
        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        Constraints constraints;

        if (PreferencesManager.getBoolean(PreferencesManager.WIFI_ONLY_FOR_DOWNLOAD)) {
            constraints = constraintsBuilder.setRequiredNetworkType(NetworkType.UNMETERED).build();
        } else {
            constraints = constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED).build();
        }

        builder.setConstraints(constraints);
        PeriodicWorkRequest request = builder.build();

        if (PreferencesManager.getBoolean(PreferencesManager.SYNC_ON_STARTUP)) {
            workManager.enqueue(request);
        } else {
            workManager.enqueueUniquePeriodicWork("db_sync", ExistingPeriodicWorkPolicy.KEEP, request);
        }

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(DbCleanUpWorker.class, 12, TimeUnit.HOURS)
                .setInputData(new Data.Builder()
                        .putBoolean(PreferencesManager.KEEP_UNREAD_ARTICLES, PreferencesManager.getBoolean(PreferencesManager.KEEP_UNREAD_ARTICLES))
                        .putInt(PreferencesManager.AUTO_CLEANUP_UNREAD, Integer.parseInt(PreferencesManager.getString(PreferencesManager.AUTO_CLEANUP_UNREAD)))
                        .putInt(PreferencesManager.AUTO_CLEANUP_READ, Integer.parseInt(PreferencesManager.getString(PreferencesManager.AUTO_CLEANUP_READ)))
                        .build())
                .build();
        workManager.enqueue(periodicWorkRequest);
    }
}
