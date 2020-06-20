package com.zookanews.egyptlatestnews.db.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.gson.Gson;
import com.zookanews.egyptlatestnews.db.AppDB;
import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.helper.DataManager;
import com.zookanews.egyptlatestnews.interfaces.NewsListener;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.remote.ApiClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class ArticleRepo {

    private ArticleDao articleDao;
    private ExecutorService executor;
    private boolean refreshed;

    public ArticleRepo(Application application) {
        AppDB db = AppDB.getDatabase(application);
        articleDao = db.articleDao();
        executor = new ScheduledThreadPoolExecutor(10);
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

    public LiveData<PagedList<Article>> getCategoryArticles(String categoryName) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        return new LivePagedListBuilder<>(articleDao.getCategoryArticles(categoryName), config).build();
    }

    public LiveData<PagedList<Article>> getWebsiteArticles(String websiteName) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        return new LivePagedListBuilder<>(articleDao.getWebsiteArticles(websiteName), config).build();
    }

    public LiveData<PagedList<Article>> getFavoriteArticles() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        return new LivePagedListBuilder<>(articleDao.getFavoriteArticles(), config).build();
    }

    public LiveData<PagedList<Article>> getSearchResults(String query) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        return new LivePagedListBuilder<>(articleDao.getSearchResults("%" + query + "%"), config).build();
    }

    public void updateReadStatus(int articleId, boolean isRead) {
        executor.execute(() -> articleDao.updateReadStatus(articleId, isRead));
    }

    public LiveData<Integer> getCountOfCategoryUnreadArticles(String categoryName) {
        return articleDao.getCountOfCategoryUnreadArticles(categoryName);
    }

    public void clearDB() {
        executor.execute(() -> articleDao.clearDB());
    }

    public LiveData<Integer> getCountOfWebsiteUnreadArticles(String websiteName) {
        return articleDao.getCountOfWebsiteUnreadArticles(websiteName);
    }

    public void updateFavoriteStatus(int articleId, boolean isFavorite) {
        executor.execute(() -> articleDao.updateFavoriteStatus(articleId, isFavorite));
    }

    public Article getArticleById(int articleId) {
        final Article[] article = new Article[1];
        executor.execute(() -> {
            article[0] = articleDao.getArticleById(articleId);
            Timber.d(new Gson().toJson(article[0]));
        });
        return article[0];
    }

    public LiveData<Article> getArticleByIdLiveData(int articleId) {
        return articleDao.getArticleByIdLiveData(articleId);
    }

    public void loadNewArticles() {
        ApiClient.loadNews(ApiClient.getInstance(), DataManager.feeds, new NewsListener() {
            @Override
            public void onNewsSuccess(List<Article> newsArticles) {
                Timber.d("News Articles found: " + newsArticles.size());
                for (Article article : newsArticles) {
                    Timber.d("Inserted Article: %s", article.getTitle());
                    executor.execute(() -> articleDao.insertArticle(article));
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

    public boolean getRefreshed() {
        return refreshed;
    }
}
