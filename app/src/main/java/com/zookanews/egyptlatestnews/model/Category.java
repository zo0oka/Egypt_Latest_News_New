package com.zookanews.egyptlatestnews.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "menu_id")
    private int menuId;

    public Category(int id, @NonNull String name, int menuId, String title) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.title = title;
    }

    @Ignore
    public Category(@NonNull String name, int menuId, String title) {
        this.name = name;
        this.menuId = menuId;
        this.title = title;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class CategoryNames {
        public static final String LATEST_NEWS = "latest_news";
        public static final String POLITICS = "politics";
        public static final String ACCIDENTS = "accidents";
        public static final String FINANCE = "finance";
        public static final String SPORTS = "sports";
        public static final String WOMAN = "woman";
        public static final String ARTS = "arts";
        public static final String TECHNOLOGY = "technology";
        public static final String VIDEOS = "videos";
        public static final String AUTOMOTIVE = "automotive";
        public static final String INVESTIGATIONS = "investigations";
        public static final String CULTURE = "culture";
        public static final String TRAVEL = "travel";
        public static final String HEALTH = "health";
        public static final String OPINION = "opinion";
        public static final String WORLD_ARAB = "world_arab";
        public static final String FASHION = "fashion";
        public static final String CORONA = "corona";
    }
}
