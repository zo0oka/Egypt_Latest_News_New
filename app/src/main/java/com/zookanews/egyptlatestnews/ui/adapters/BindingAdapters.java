package com.zookanews.egyptlatestnews.ui.adapters;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.helper.WebViewLoader;

import java.util.Calendar;
import java.util.Locale;

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

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("timeAgo")
    public static void getFormattedTimeAgo(TextView textView, long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();
        long timePeriod = currentTime - timeMillis;
        Timber.d("Current Time: " + currentTime + " Time Period: " + timePeriod);

        long millisInSecond = 1000;
        long secondsInMinute = 60;
        long minutesInHour = 60;
        long hoursInDay = 24;
        long daysInMonth = 30;
        long daysInYear = 365;

        long millisInYear = daysInYear * hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInMonth = daysInMonth * hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInDay = hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInHour = minutesInHour * secondsInMinute * millisInSecond;
        long millisInMinute = secondsInMinute * millisInSecond;

        if (timePeriod < millisInMinute) {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInSecond, textView.getContext().getString(R.string.seconds_ago)));
        } else if (timePeriod < millisInHour) {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInMinute, textView.getContext().getString(R.string.minutes_sgo)));
        } else if (timePeriod < millisInDay) {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInHour, textView.getContext().getString(R.string.hours_ago)));
        } else if (timePeriod < millisInMonth) {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInDay, textView.getContext().getString(R.string.days_ago)));
        } else if (timePeriod < millisInYear) {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInMonth, textView.getContext().getString(R.string.months_ago)));
        } else {
            textView.setText(String.format(Locale.ENGLISH, "%d%s", timePeriod / millisInYear, textView.getContext().getString(R.string.years_ago)));
        }
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

    @BindingAdapter("loadArticleUrl")
    public static void loadArticleUrl(WebView webView, String url) {
        WebViewLoader loader = new WebViewLoader(webView);
        loader.setSettings();
        loader.loadUrl(url);
    }

    @BindingAdapter("setArticleDescription")
    public static void setArticleDescription(TextView textView, String description) {
        Timber.d("Description: " + description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(description));
        }
    }
}
