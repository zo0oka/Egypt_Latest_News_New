package com.zookanews.egyptlatestnews.worker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.helper.DataManager;
import com.zookanews.egyptlatestnews.helper.PreferencesManager;
import com.zookanews.egyptlatestnews.interfaces.NewsListener;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.remote.ApiClient;
import com.zookanews.egyptlatestnews.ui.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import timber.log.Timber;

import static com.zookanews.egyptlatestnews.helper.Constants.ARTICLE_NOTIFICATION_GROUP_KEY;
import static com.zookanews.egyptlatestnews.helper.Constants.NOTIFICATION_CHANNEL_ID;
import static com.zookanews.egyptlatestnews.helper.Constants.NOTIFICATION_SUMMARY_ID;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 18 Jun, 2020.
 * Have a nice day!
 */
public class DbSyncWorker extends Worker {

    public static final String NOTIFICATION_ARTICLE_ID = "notification_article_id";
    int noOfNotifications = 0;
    private ArticleDao articleDao;
    private NotificationManagerCompat notificationManagerCompat;
    private ExecutorService executorService;
    private ArrayList<Long> ids;

    public DbSyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Timber.d("Worker Started " + DbSyncWorker.class.getSimpleName() + " at %s", Calendar.getInstance().getTime().toString());
        articleDao = AppDB.getDatabase(context).articleDao();
        executorService = new ScheduledThreadPoolExecutor(20);
        notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        if (PreferencesManager.getBoolean(PreferencesManager.AUTOMATIC_BACKGROUND_SYNC)) {
            updateDB();
        }
        Timber.d("Worker Finished " + DbSyncWorker.class.getSimpleName() + " at %s", Calendar.getInstance().getTime().toString());
        return Result.success();
    }

    private void updateDB() {
        createNotificationChannel(PreferencesManager.getBoolean(PreferencesManager.VIBRATE), PreferencesManager.getBoolean(PreferencesManager.LIGHT));
        ids = new ArrayList<>();
        ApiClient.loadNews(ApiClient.getInstance(), DataManager.feeds, new NewsListener() {
            @Override
            public void onNewsSuccess(List<Article> newsArticles) {
                for (Article article : newsArticles) {
                    createArticleNotification(article);
                }
            }

            @Override
            public void onNewsError(String error) {

            }

            @Override
            public void onNewsFailure(String failure) {

            }
        });
    }

    private void createArticleNotification(Article article) {
        executorService.execute(() -> {
            long id = articleDao.insertArticle(article);
            Timber.d("Inserted Article ID: %s", id);
            if (id != -1) {
                ids.add(id);
                Timber.d("ID: " + id + " IDs Count: %s", ids.size());
                if (PreferencesManager.getBoolean(PreferencesManager.NEW_ARTICLE_NOTIFICATION)) {
                    Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    notificationIntent.putExtra(NOTIFICATION_ARTICLE_ID, id);
                    PendingIntent notificationPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                            new Random().nextInt(Integer.MAX_VALUE), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    noOfNotifications++;
                    Notification newArticleNotification =
                            new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(article.getTitle())
                                    .setContentText(article.getDescription())
                                    .setTicker(article.getTitle())
                                    .setContentIntent(notificationPendingIntent)
                                    .setGroup(ARTICLE_NOTIFICATION_GROUP_KEY)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .build();
                    newArticleNotification.flags |= Notification.FLAG_AUTO_CANCEL;

                    Notification summaryNotification =
                            new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Egypt Latest News")
                                    .setContentText(noOfNotifications + " new article(s)")
                                    .setStyle(new NotificationCompat.InboxStyle()
                                            .addLine(articleDao.getArticleById(ids.get(ids.size() - 1).intValue()).getTitle())
                                            .addLine(articleDao.getArticleById(ids.get(ids.size() - 2).intValue()).getTitle())
                                            .addLine(articleDao.getArticleById(ids.get(ids.size() - 3).intValue()).getTitle())
                                            .addLine(articleDao.getArticleById(ids.get(ids.size() - 4).intValue()).getTitle())
                                            .addLine(articleDao.getArticleById(ids.get(ids.size() - 5).intValue()).getTitle())
                                            .setBigContentTitle(ids.size() + " new article(s)")
                                            .setSummaryText("+" + (noOfNotifications - 5) + " more article(s)"))
                                    .setNumber(noOfNotifications)
                                    .setContentIntent(notificationPendingIntent)
                                    .setGroupSummary(true)
                                    .setGroup(ARTICLE_NOTIFICATION_GROUP_KEY)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .build();

                    notificationManagerCompat.notify(new Random().nextInt(Integer.MAX_VALUE), newArticleNotification);
                    notificationManagerCompat.notify(NOTIFICATION_SUMMARY_ID, summaryNotification);
                }
            } else {
                Timber.d("News ID == -1");
            }
            Timber.d("IDs Added: " + ids.size());
        });
    }

    private void createNotificationChannel(Boolean vibrate, Boolean light) {
        if (notificationManagerCompat != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                    notificationManagerCompat.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
                NotificationChannel notificationChannel = (new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Egypt Latest News",
                        NotificationManager.IMPORTANCE_HIGH));
                notificationChannel.enableVibration(vibrate);
                notificationChannel.enableLights(light);
                notificationChannel.setDescription("Egypt Latest News Notification Channel");
                notificationChannel.setLightColor(Color.WHITE);
                notificationManagerCompat.createNotificationChannel(notificationChannel);
            }
        }
    }
}
