package com.zookanews.egyptlatestnews.interfaces;

import android.view.View;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 06 Jun, 2020.
 * Have a nice day!
 */
public class ClickHandlers {

    public interface NewsArticleList {
        void onArticleClick(View view, int articleId);

        void onFavorite(int articleId, boolean isFavorite);
    }

    public interface NewsArticle {
        void onOpenWebsite(View view, String link);
    }
}
