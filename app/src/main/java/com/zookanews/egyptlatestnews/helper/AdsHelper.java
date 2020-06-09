package com.zookanews.egyptlatestnews.helper;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.zookanews.egyptlatestnews.BuildConfig;
import com.zookanews.egyptlatestnews.R;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 3/27/20.
 * Have a nice day!
 */
public class AdsHelper {

    public static void initAds(Context context) {
        MobileAds.initialize(context, context.getString(R.string.ADMOB_APP_ID));
        List<String> testDeviceIds = Collections.singletonList("548483E778E0749009F9BFD3D3A2636D");
        if (BuildConfig.DEBUG) {
            RequestConfiguration configuration =
                    new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
            MobileAds.setRequestConfiguration(configuration);
        }
    }

    public static void injectAds(FragmentActivity context, List<Object> itemsWithAds, String bannerAdUnitId, int itemsPerAds) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

//        AdSize adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
        AdSize adSize = AdSize.LARGE_BANNER;

        addBannerAds(context, itemsWithAds, bannerAdUnitId, adSize, itemsPerAds);
    }

    private static void addBannerAds(Context context, List<Object> itemsWithAds, String bannerAdUnitId, AdSize adSize, int itemsPerAds) {
        for (int i = 0; i <= itemsWithAds.size(); i += itemsPerAds) {
            final AdView adView = new AdView(context);
            adView.setAdSize(adSize);
            adView.setAdUnitId(bannerAdUnitId);
            itemsWithAds.add(i, adView);
        }

        Timber.d("Mixed Items: %s", itemsWithAds.size());
        loadBannerAds(itemsWithAds, itemsPerAds);
    }

    private static void loadBannerAds(List<Object> itemsWithAds, int itemsPerAds) {
        loadBannerAd(0, itemsWithAds, itemsPerAds);
    }

    private static void loadBannerAd(int index, List<Object> itemsWithAds, int itemsPerAds) {

        if (index >= itemsWithAds.size()) {
            return;
        }

        Object item = itemsWithAds.get(index);
        if (!(item instanceof AdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a banner ad.");
        }

        final AdView adView = (AdView) item;

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                loadBannerAd(index + itemsPerAds, itemsWithAds, itemsPerAds);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Timber.e("The previous banner ad failed to load. Attempting to load the next banner ad in the items list.");
                loadBannerAd(index + itemsPerAds, itemsWithAds, itemsPerAds);
            }
        });

        adView.loadAd(new AdRequest.Builder().build());
    }
}
