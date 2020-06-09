package com.zookanews.egyptlatestnews.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.zookanews.egyptlatestnews.db.repos.ArticleRepo;
import com.zookanews.egyptlatestnews.model.Article;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class AppViewModel extends AndroidViewModel {

    private final LiveData<PagedList<Article>> articles;
    private ArticleRepo articleRepo;

    public AppViewModel(@NonNull Application application) {
        super(application);
        articleRepo = new ArticleRepo(application);
        articles = articleRepo.getAllArticles();
    }

    public LiveData<PagedList<Article>> getArticles() {
        return articles;
    }
}
