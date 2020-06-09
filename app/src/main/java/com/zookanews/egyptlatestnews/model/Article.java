package com.zookanews.egyptlatestnews.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "articles", indices = {@Index(value = {"link"}, unique = true)})
public class Article {
    public static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getLink().equals(newItem.getLink());
        }
    };
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @NonNull
    @ColumnInfo(name = "link")
    private String link;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "date")
    private Long pubDate;
    @ColumnInfo(name = "thumb_url")
    private String thumbUrl;
    @ColumnInfo(name = "website_name")
    private String websiteName;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "isRead")
    private Boolean isRead;
    @ColumnInfo(name = "isFavorite")
    private Boolean isFavorite;

    public Article(int id, String title, @NonNull String link, String description, String content, Long pubDate, String thumbUrl, String websiteName, String categoryName, Boolean isRead, Boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.content = content;
        this.pubDate = pubDate;
        this.thumbUrl = thumbUrl;
        this.websiteName = websiteName;
        this.categoryName = categoryName;
        this.isRead = isRead;
        this.isFavorite = isFavorite;
    }

    @Ignore
    public Article(String title, @NonNull String link, String description, String content, Long pubDate, String thumbUrl, String websiteName, String categoryName, Boolean isRead, Boolean isFavorite) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.content = content;
        this.pubDate = pubDate;
        this.thumbUrl = thumbUrl;
        this.websiteName = websiteName;
        this.categoryName = categoryName;
        this.isRead = isRead;
        this.isFavorite = isFavorite;
    }

    public static DiffUtil.ItemCallback<Article> getDiffCallback() {
        return DIFF_CALLBACK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getLink() {
        return link;
    }

    public void setLink(@NonNull String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + title + "\n"
                + "Link: " + link + "\n"
                + "Description: " + description + "\n"
                + "Date: " + pubDate + "\n"
                + "Thumbnail: " + thumbUrl + "\n"
                + "Website: " + websiteName + "\n"
                + "Category: " + categoryName + "\n"
                + "Content: " + content + "\n"
                + "Read: " + isRead + ".";
    }
}
