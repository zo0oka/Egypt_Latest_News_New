package com.zookanews.egyptlatestnews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentWebViewBinding;
import com.zookanews.egyptlatestnews.helper.PreferencesManager;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

import static com.zookanews.egyptlatestnews.helper.Constants.ADMOB_INTERTITIALAD_UNIT_ID;

public class WebViewFragment extends Fragment {

    public static final String LINK = "article_link";
    private String articleLink;
    private FragmentWebViewBinding binding;
    private InterstitialAd mInterstitialAd;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleLink = getArguments().getString(LINK);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int adCounter = PreferencesManager.getInt(PreferencesManager.KEY_AD_COUNTER);
        PreferencesManager.saveInt(adCounter + 1, PreferencesManager.KEY_AD_COUNTER);

        if (PreferencesManager.getInt(PreferencesManager.KEY_AD_COUNTER) % 3 == 0) {
            InterstitialAd mInterstitialAd = new InterstitialAd(view.getContext());
            mInterstitialAd.setAdUnitId(ADMOB_INTERTITIALAD_UNIT_ID);
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    openWebLink();
                }

                @Override
                public void onAdLoaded() {
                    mInterstitialAd.show();
                }
            });
        }

        openWebLink();
    }

    private void openWebLink() {
        binding.webViewProgress.setVisibility(View.VISIBLE);

        binding.setLink(articleLink);

        binding.articleWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (isAdded()) {
                    Timber.d("Is Added Checked & Passed");
                    ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(view.getTitle());
                    binding.webViewProgress.setVisibility(View.GONE);
                }
            }
        });
    }
}