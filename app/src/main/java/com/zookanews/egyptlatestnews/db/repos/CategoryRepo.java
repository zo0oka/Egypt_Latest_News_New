package com.zookanews.egyptlatestnews.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.CategoryDao;
import com.zookanews.egyptlatestnews.model.Category;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 17 Jun, 2020.
 * Have a nice day!
 */
public class CategoryRepo {

    private final CategoryDao categoryDao;
    private ExecutorService executor;

    public CategoryRepo(Application application) {
        AppDB db = AppDB.getDatabase(application);
        categoryDao = db.categoryDao();
        executor = new ScheduledThreadPoolExecutor(10);
    }

    public LiveData<Category> getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }
}
