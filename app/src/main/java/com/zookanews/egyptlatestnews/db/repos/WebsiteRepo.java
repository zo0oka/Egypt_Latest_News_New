package com.zookanews.egyptlatestnews.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.WebsiteDao;
import com.zookanews.egyptlatestnews.model.Website;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 17 Jun, 2020.
 * Have a nice day!
 */
public class WebsiteRepo {

    private final WebsiteDao websiteDao;
    private ExecutorService executor;

    public WebsiteRepo(Application application) {
        AppDB db = AppDB.getDatabase(application);
        websiteDao = db.websiteDao();
        executor = new ScheduledThreadPoolExecutor(10);
    }

    public LiveData<Website> getWebsiteByName(String websiteName) {
        return websiteDao.getWebsiteByName(websiteName);
    }
}
