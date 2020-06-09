package com.zookanews.egyptlatestnews.interfaces;

import com.zookanews.egyptlatestnews.model.Article;

import java.util.List;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public interface NewsListener {

    void onNewsSuccess(List<Article> newsArticles);

    void onNewsError(String error);

    void onNewsFailure(String failure);

}
