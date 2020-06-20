package com.zookanews.egyptlatestnews.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.helper.PreferencesManager;

import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 21 Jun, 2020.
 * Have a nice day!
 */
public class DbCleanUpWorker extends Worker {

    private boolean keepUnreadArticles;
    private int unreadOlderThan;
    private int readOlderThan;

    public DbCleanUpWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Timber.d("Worker Started " + DbCleanUpWorker.class.getSimpleName() + " at %s", Calendar.getInstance().getTime().toString());
        keepUnreadArticles = workerParams.getInputData().getBoolean(PreferencesManager.KEEP_UNREAD_ARTICLES, true);
        unreadOlderThan = workerParams.getInputData().getInt(PreferencesManager.AUTO_CLEANUP_UNREAD, 2);
        readOlderThan = workerParams.getInputData().getInt(PreferencesManager.AUTO_CLEANUP_READ, 2);

    }

    @NonNull
    @Override
    public Result doWork() {
        Timber.d("Worker Finished " + DbCleanUpWorker.class.getSimpleName() + " at %s", Calendar.getInstance().getTime().toString());
        AppDB db = AppDB.getDatabase(getApplicationContext());
        ArticleDao dao = db.articleDao();
        long unreadTimeMillis = unreadOlderThan * 24 * 60 * 60 * 1000;
        long readTimeMillis = readOlderThan * 24 * 60 * 60 * 1000;
        if (!keepUnreadArticles) {
            dao.deleteArticlesOlderThan(Calendar.getInstance().getTimeInMillis() - unreadTimeMillis, 0);
        }
        dao.deleteArticlesOlderThan(Calendar.getInstance().getTimeInMillis() - readTimeMillis, 1);
        return Result.success();
    }
}
