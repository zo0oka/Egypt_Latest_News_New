package com.zookanews.egyptlatestnews.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "websites", indices = {@Index(value = {"name"}, unique = true)})
public class Website {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "menu_id")
    private int menuId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Website(int id, @NotNull String name, int menuId, String title) {
        this.id = id;
        this.menuId = menuId;
        this.title = title;
        this.name = name;
    }

    @Ignore
    public Website(@NotNull String name, int menuId, String title) {
        this.title = title;
        this.menuId = menuId;
        this.name = name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public static class WebsiteNames {
        public static final String ALMASRY_ALYOUM = "almasry_alyoum";
        public static final String ALWATAN = "alwatan";
        public static final String ALDOSTOUR = "aldostour";
        public static final String AKHBARAK = "akhbarak";
        public static final String ALWAFD = "alwafd";
        public static final String BBC_ARABIA = "bbc_arabic";
        public static final String ALFAGR = "alfagr";
        public static final String ROSE_ALYOUSEF = "rose_alyousef";
        public static final String AKHBAR_ELYOUM = "akhbar_elyoum";
        public static final String SADA_ELBALAD = "sada_albalad";
        public static final String BAWABET_VETO = "bawabet_veto";
        public static final String ALMOGAZ = "almogaz";
        public static final String ALYOUM7 = "alyoum7";
        public static final String AL_SHOROUK = "al_shorouk";
        public static final String ARAB_FINANCE = "arab_finance";
    }
}
