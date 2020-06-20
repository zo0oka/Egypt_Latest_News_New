package com.zookanews.egyptlatestnews.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.zookanews.egyptlatestnews.R;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 5/2/20.
 * Have a nice day!
 */
@SuppressWarnings("rawtypes")
public final class PreferencesManager {

    public static final String AUTOMATIC_BACKGROUND_SYNC = "pref_key_automatic_background_sync";
    public static final String SYNC_FREQUENCY = "pref_key_sync_frequency";
    public static final String SYNC_ON_STARTUP = "pref_key_sync_on_startup";
    public static final String WIFI_ONLY_FOR_DOWNLOAD = "pref_key_wifi_only_for_download";
    public static final String KEEP_UNREAD_ARTICLES = "pref_key_keep_unread_articles";
    public static final String AUTO_CLEANUP_READ = "pref_key_auto_cleanup_read";
    public static final String AUTO_CLEANUP_UNREAD = "pref_key_auto_cleanup_unread";
    public static final String NEW_ARTICLE_NOTIFICATION = "pref_key_new_article_notifications";
    public static final String VIBRATE = "pref_key_vibrate";
    public static final String LIGHT = "pref_key_light";
    public static final String KEY_AD_COUNTER = "ad_counter";

    private static SharedPreferences pref;
    private static WeakReference<Context> contextWeakReference;

    public static void init(Application application) {
        androidx.preference.PreferenceManager.setDefaultValues(application, R.xml.preferences, false);
        contextWeakReference = new WeakReference<>(application);
        pref = PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get());
        Timber.d("Preferences Manager Initialized");
    }

    public static boolean hasKey(String key) {
        if (contextWeakReference != null) {
            return pref.contains(key);
        }
        return false;
    }

    public static void save(Object o) {
        pref.edit().putString(o.getClass().getName(), new Gson().toJson(o))
                .apply();
        Timber.d("**** SAVED **** Object: " + o.getClass().getName() + " with value: " + new Gson().toJson(o));
    }

    public static String getString(String key) {
        return pref.getString(key, "");
    }

    public static int getInt(String key) {
        return pref.getInt(key, 0);
    }

    public static void saveString(String value, String key) {
        pref.edit().putString(key, value)
                .apply();
        Timber.d("**** SAVED **** String: " + key + " with value: " + value);
    }

    public static void saveBoolean(boolean value, String key) {
        pref.edit().putBoolean(key, value)
                .apply();
        Timber.d("****SAVED**** Boolean: " + key + " with value: " + value);
    }

    public static void saveInt(int value, String key) {
        pref.edit().putInt(key, value)
                .apply();
        Timber.d("**** SAVED **** Integer: " + key + " with value: " + value);
    }

    public static boolean getBoolean(String key) {
        return pref.getBoolean(key, true);
    }

    public static void saveDate(long value, String key) {
        pref.edit().putLong(key, value)
                .apply();
        Timber.d("****SAVED**** Date: " + key + " with value: " + value);
    }

    public static long getDate(String key) {
        return pref.getLong(key, 0);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return pref.getBoolean(key, defValue);
    }

    public static void clear(Class c) {
        pref.edit().putString(c.getName(), null)
                .apply();
    }

    public static void delete(String key) {
        pref.edit().remove(key)
                .apply();
    }

    @Nullable
    public static Object load(Class c) {
        return new Gson().fromJson(pref.getString(c.getName(), null), c);
    }

    public static void clearAppData() {
        pref.edit().clear()
                .apply();
    }
}
