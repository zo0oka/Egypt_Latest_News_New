package com.zookanews.egyptlatestnews.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.zookanews.egyptlatestnews.db.dao.ArticleDao;
import com.zookanews.egyptlatestnews.db.dao.CategoryDao;
import com.zookanews.egyptlatestnews.db.dao.FeedDao;
import com.zookanews.egyptlatestnews.db.dao.WebsiteDao;
import com.zookanews.egyptlatestnews.helper.DataManager;
import com.zookanews.egyptlatestnews.interfaces.NewsListener;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.model.Category;
import com.zookanews.egyptlatestnews.model.Feed;
import com.zookanews.egyptlatestnews.model.Website;
import com.zookanews.egyptlatestnews.remote.ApiClient;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import timber.log.Timber;

@Database(entities = {Article.class, Category.class, Feed.class, Website.class}, version = 1, exportSchema = false)
//@TypeConverters({TypeConverters.class})
public abstract class AppDB extends RoomDatabase {

    private static volatile AppDB INSTANCE;
    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateDB(INSTANCE);
        }
    };

    private static void populateDB(AppDB db) {
        Executor executor = Executors.newFixedThreadPool(5);
        Timber.d("DB Population...");
        ApiClient.loadNews(ApiClient.getInstance(), DataManager.feeds, new NewsListener() {
            @Override
            public void onNewsSuccess(List<Article> newsArticles) {
                for (Article article : newsArticles) {
                    Timber.d("Inserted Article: " + article.getTitle());
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.articleDao().insertArticle(article);
                        }
                    });
                }
            }

            @Override
            public void onNewsError(String error) {

            }

            @Override
            public void onNewsFailure(String failure) {

            }
        });
        for (Category category : DataManager.categories) {
            Timber.d("Inserted Category: " + category.getName());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    db.categoryDao().insertCategory(category);
                }
            });
        }

        for (Website website : DataManager.websites) {
            Timber.d("Inserted Website: " + website.getName());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    db.websiteDao().insertWebsite(website);
                }
            });
        }

    }

    public static AppDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract ArticleDao articleDao();

    public abstract CategoryDao categoryDao();

    public abstract FeedDao feedDao();

    public abstract WebsiteDao websiteDao();
}
