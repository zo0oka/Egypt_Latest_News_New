package com.zookanews.egyptlatestnews.helper;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressWarnings("deprecation")
public class WebViewLoader {

    private WebView webView;

    public WebViewLoader(WebView webView) {
        this.webView = webView;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setSettings() {
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        webView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
    }

    public void loadHtmlData(String data) {
//        webView.loadData("<style>img{display: block;margin-left: auto;margin-right: auto;height: auto;max-width: auto }</style>"
//                + data, "text/html", "UTF-8");
        webView.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + data, "text/html", "UTF-8", null);
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }
}
