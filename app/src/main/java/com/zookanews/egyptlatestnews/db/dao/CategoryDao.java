package com.zookanews.egyptlatestnews.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zookanews.egyptlatestnews.model.Category;


@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Query("SELECT * FROM categories WHERE name = :categoryName")
    LiveData<Category> getCategoryByName(String categoryName);

}
