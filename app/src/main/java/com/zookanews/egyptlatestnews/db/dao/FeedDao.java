package com.zookanews.egyptlatestnews.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zookanews.egyptlatestnews.model.Feed;

import java.util.List;

@Dao
public interface FeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeed(Feed feed);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeeds(List<Feed> feeds);

    @Query("SELECT * FROM feeds ORDER BY id DESC")
    List<Feed> getAllFeeds();
}
