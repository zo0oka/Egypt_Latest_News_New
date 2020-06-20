package com.zookanews.egyptlatestnews.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 5/2/20.
 * Have a nice day!
 */
public class LanguageManager {


    private static final String PREFS_LANG_KEY = "com.uaebarq.uaebarq.lang_key";
    private static final String PREFS_IS_SET_BY_USER = "com.uaebarq.uaebarq.lang_set_by_user";
    private static Application sAppContext;
    private static Language sCurrentLanguage;

    public static void init(Application application) {
        sAppContext = application;
        loadLanguage();
    }

    public static void setLanguage(Activity activity, Language language) {
        setLanguage(activity, activity.getClass(), language);
    }

    public static void setLanguage(Activity activity, Class activity2, Language language) {
        saveLanguage(language);
        apply(activity, activity2);
    }

    public static void updateLocale(Context baseContext) {
        Locale.setDefault(sCurrentLanguage.locale);
        Resources resources = baseContext.getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            configuration.setLocale(sCurrentLanguage.locale);
        else configuration.locale = sCurrentLanguage.locale;
        baseContext.getResources().updateConfiguration(configuration, baseContext.getResources().getDisplayMetrics());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            baseContext.createConfigurationContext(configuration);
        else resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static Language getLanguage() {
        return sCurrentLanguage;
    }

    public static String path() {
        if (sCurrentLanguage.equals(Language.AR)) return Language.AR.path;
        return Language.EN.path;
    }

    public static Locale getLocale() {
        return sCurrentLanguage.locale;
    }

    private static void loadLanguage() {
        Language saved = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .fromJson(getDefaultSharedPreferences(sAppContext).getString(PREFS_LANG_KEY, null), Language.class);
        sCurrentLanguage = saved != null ? saved : Language.AR;
    }

    public static void saveLanguage(Language l) {
        sCurrentLanguage = l;
        getDefaultSharedPreferences(sAppContext).edit().putString(PREFS_LANG_KEY, new Gson().toJson(l)).apply();
        getDefaultSharedPreferences(sAppContext).edit().putBoolean(PREFS_IS_SET_BY_USER, true).apply();
    }

    private static void apply(Activity activity, Class activity2) {
        activity.startActivity(new Intent(activity, activity2).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        ActivityCompat.finishAffinity(activity);
    }

    public enum Language {

        EN("English", "en", new Locale("en", "US")),
        //        FR("French", "fr", new Locale("fr", "FR")),
//        ES("Spanish", "es", new Locale("es", "ES")),
//        TR("Turkish", "tr", new Locale("tr", "TR")),
        AR("Arabic", "ar", new Locale("ar", "AE"));

        public String title;
        public String path;
        public Locale locale;

        Language(String title, String path, Locale locale) {
            this.title = title;
            this.path = path;
            this.locale = locale;
        }
    }
}
