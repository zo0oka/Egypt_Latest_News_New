package com.zookanews.egyptlatestnews.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;

import com.zookanews.egyptlatestnews.db.repos.ArticleRepo;
import com.zookanews.egyptlatestnews.model.Article;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class ArticleViewModel extends AndroidViewModel {

    private LiveData<PagedList<Article>> articles;
    private LiveData<PagedList<Article>> favoriteArticles;
    private ArticleRepo articleRepo;
    private MutableLiveData<String> searchQuery = new MediatorLiveData<>();
    private LiveData<PagedList<Article>> searchResults = Transformations.switchMap(searchQuery, new Function<String, LiveData<PagedList<Article>>>() {
        @Override
        public LiveData<PagedList<Article>> apply(String input) {
            return articleRepo.getSearchResults(input);
        }
    });
    private MutableLiveData<String> categoryName = new MutableLiveData<>();
    private LiveData<PagedList<Article>> categoryArticles = Transformations.switchMap(categoryName, new Function<String, LiveData<PagedList<Article>>>() {
        @Override
        public LiveData<PagedList<Article>> apply(String input) {
            return articleRepo.getCategoryArticles(input);
        }
    });
    private MutableLiveData<String> websiteName = new MutableLiveData<>();
    private LiveData<PagedList<Article>> websiteArticles = Transformations.switchMap(websiteName, new Function<String, LiveData<PagedList<Article>>>() {
        @Override
        public LiveData<PagedList<Article>> apply(String input) {
            return articleRepo.getWebsiteArticles(input);
        }
    });

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        articleRepo = new ArticleRepo(application);
        articles = articleRepo.getAllArticles();
        favoriteArticles = articleRepo.getFavoriteArticles();
    }

    public void clearDB() {
        articleRepo.clearDB();
    }

    public Article getArticleById(int articleId) {
        return articleRepo.getArticleById(articleId);
    }

    public LiveData<Article> getArticleByIdLiveData(int articleId) {
        return articleRepo.getArticleByIdLiveData(articleId);
    }

    public LiveData<PagedList<Article>> getArticles() {
        return articles;
    }

    public LiveData<Integer> getCountOfCategoryUnreadArticles(String categoryName) {
        return articleRepo.getCountOfCategoryUnreadArticles(categoryName);
    }

    public LiveData<Integer> getCountOfWebsiteUnreadArticles(String categoryName) {
        return articleRepo.getCountOfWebsiteUnreadArticles(categoryName);
    }

    public void loadNewArticles() {
        articleRepo.loadNewArticles();
    }

    public LiveData<PagedList<Article>> getCategoryArticles() {
        return categoryArticles;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.setValue(categoryName);
    }

    public LiveData<PagedList<Article>> getWebsiteArticles() {
        return websiteArticles;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName.setValue(websiteName);
    }

    public LiveData<PagedList<Article>> getFavoriteArticles() {
        return favoriteArticles;
    }

    public void updateFavoriteStatus(int articleId, boolean isFavorite) {
        articleRepo.updateFavoriteStatus(articleId, isFavorite);
    }

    public void updateReadStatus(int articleId, boolean isRead) {
        articleRepo.updateReadStatus(articleId, isRead);
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery.setValue(searchQuery);
    }

    public LiveData<PagedList<Article>> getSearchResults() {
        return searchResults;
    }
}
