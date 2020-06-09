package com.zookanews.egyptlatestnews.ui.adapters;

import android.graphics.drawable.Drawable;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.zookanews.egyptlatestnews.helper.WebViewLoader;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 5/12/20.
 * Have a nice day!
 */
public class BindingAdapters {

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String imageUrl) {
        Timber.d("Image Url: %s", imageUrl);
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

//    @BindingAdapter("setDayTime")
//    public static void setDayTime(TextView textView, String dateTime) {
//        Locale locale = LanguageManager.getLanguage().locale;
//        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale);
//        SimpleDateFormat to = new SimpleDateFormat("hh:mm aa", locale);
//        Date date = null;
//        try {
//            date = from.parse(dateTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (date != null) {
//            String time = to.format(date);
//            textView.setText(time);
//        }
//    }

    @BindingAdapter("setFavoriteIcon")
    public static void setFavoriteIcon(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("loadUrl")
    public static void loadUrl(WebView webView, String url) {
        WebViewLoader loader = new WebViewLoader(webView);
        loader.setSettings();
        loader.loadUrl(url);
    }
}
