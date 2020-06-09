package com.zookanews.egyptlatestnews.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.model.Article;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class ArticleRepo {

    private ArticleDao articleDao;

    public ArticleRepo(Application application) {
        AppDB db = AppDB.getDatabase(application);
        articleDao = db.articleDao();
    }

    public LiveData<PagedList<Article>> getAllArticles() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        return new LivePagedListBuilder<>(articleDao.getAllArticles(), config).build();
    }
}
