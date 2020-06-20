package com.zookanews.egyptlatestnews.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.helper.DataManager;
import com.zookanews.egyptlatestnews.interfaces.NewsListener;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.remote.ApiClient;
import com.zookanews.egyptlatestnews.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zookanews.egyptlatestnews.helper.Constants.NOTIFICATION_CHANNEL_ID;

public class DbUpdateService extends IntentService {

    public DbUpdateService() {
        super("dbsync");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("Sync started");
        startForeground(1, createNotification());
        List<Long> ids = new ArrayList<>();
        ArticleDao articleDao = AppDB.getDatabase(getApplicationContext()).articleDao();

        ApiClient.loadNews(ApiClient.getInstance(), DataManager.feeds, new NewsListener() {
            @Override
            public void onNewsSuccess(List<Article> newsArticles) {
                if (newsArticles != null) {
                    for (Article article : newsArticles) {
                        long id = articleDao.insertArticle(new Article(
                                article.getTitle(),
                                article.getLink(),
                                article.getDescription(),
                                article.getPubDate(),
                                article.getThumbUrl(),
                                article.getWebsiteName(),
                                article.getCategoryName(),
                                false,
                                false));
                        if (id != -1) {
                            ids.add(id);
                        }
                    }
                }
                showToast(ids.size() + " new articles");
                ids.clear();
                stopForeground(true);
                stopSelf();
            }

            @Override
            public void onNewsError(String error) {

            }

            @Override
            public void onNewsFailure(String failure) {

            }
        });
    }

    private void showToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show());
    }

    private Notification createNotification() {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        Boolean light = sharedPreferences.getBoolean(LIGHT, true);
//        Boolean vibrate = sharedPreferences.getBoolean(VIBRATE, true);

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            NotificationChannel notificationChannel = (new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Egypt Latest News",
                    NotificationManager.IMPORTANCE_DEFAULT));
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription("Egypt Latest News Notification Channel");
            notificationChannel.setLightColor(Color.WHITE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle("Getting latest news")
                .setContentText("Loading...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(notificationPendingIntent)
                .setTicker("Sync in progress")
                .setOngoing(true)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_SERVICE);
        return builder.build();
    }
}
