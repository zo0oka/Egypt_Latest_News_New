package com.zookanews.egyptlatestnews.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zookanews.egyptlatestnews.model.Website;

import java.util.List;

@Dao
public interface WebsiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWebsite(Website website);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWebsites(List<Website> websites);

    @Query("SELECT * FROM websites WHERE name = :websiteName")
    Website getWebsiteByName(String websiteName);
}
