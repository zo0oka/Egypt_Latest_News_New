package com.zookanews.egyptlatestnews.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "feeds", indices = {@Index(value = {"rss_link"}, unique = true)})
public class Feed {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "rss_link")
    private String rssLink;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @ColumnInfo(name = "website_name")
    private String websiteName;

    public Feed(int id, @NonNull String rssLink, String categoryName, String websiteName) {
        this.id = id;
        this.rssLink = rssLink;
        this.categoryName = categoryName;
        this.websiteName = websiteName;
    }

    @Ignore
    public Feed(@NonNull String rssLink, String categoryName, String websiteName) {
        this.rssLink = rssLink;
        this.categoryName = categoryName;
        this.websiteName = websiteName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(@NonNull String rssLink) {
        this.rssLink = rssLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }
}
