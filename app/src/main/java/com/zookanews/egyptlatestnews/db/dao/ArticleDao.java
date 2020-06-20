package com.zookanews.egyptlatestnews.db.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zookanews.egyptlatestnews.model.Article;

@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertArticle(Article article);

    @Query("SELECT * FROM articles ORDER BY date DESC")
    DataSource.Factory<Integer, Article> getAllArticles();

    @Query("DELETE FROM articles where id NOT IN (SELECT id from articles ORDER BY id DESC LIMIT 5000)")
    void clearDB();

    @Query("SELECT * FROM articles WHERE category_name = :categoryName ORDER BY date DESC")
    DataSource.Factory<Integer, Article> getCategoryArticles(String categoryName);

    @Query("SELECT * FROM articles WHERE website_name = :websiteName ORDER BY date DESC")
    DataSource.Factory<Integer, Article> getWebsiteArticles(String websiteName);

    @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY date DESC")
    DataSource.Factory<Integer, Article> getFavoriteArticles();

    @Query("UPDATE articles SET isRead = :isRead WHERE id = :articleId")
    void updateReadStatus(int articleId, Boolean isRead);

    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE id = :articleId")
    void updateFavoriteStatus(int articleId, Boolean isFavorite);

    @Query("SELECT * FROM articles WHERE id = :articleId LIMIT 1")
    Article getArticleById(int articleId);

    @Query("SELECT * FROM articles WHERE id = :articleId LIMIT 1")
    LiveData<Article> getArticleByIdLiveData(int articleId);

    @Query("SELECT * FROM articles WHERE description COLLATE NOCASE LIKE :searchQuery OR title COLLATE NOCASE LIKE :searchQuery ORDER BY date DESC")
    DataSource.Factory<Integer, Article> getSearchResults(String searchQuery);

    // TODO
    @Query("DELETE FROM articles WHERE date <= :timeMillis AND isRead = :isRead")
    void deleteArticlesOlderThan(long timeMillis, int isRead);

    @Query("SELECT COUNT(*) FROM articles WHERE isRead = 0 AND category_name = :categoryName")
    LiveData<Integer> getCountOfCategoryUnreadArticles(String categoryName);

    @Query("SELECT COUNT(*) FROM articles WHERE isRead = 0 AND website_name = :websiteName")
    LiveData<Integer> getCountOfWebsiteUnreadArticles(String websiteName);
}
