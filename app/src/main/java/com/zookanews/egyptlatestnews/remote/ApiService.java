package com.zookanews.egyptlatestnews.remote;

import com.zookanews.egyptlatestnews.model.RssFeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 06 Jun, 2020.
 * Have a nice day!
 */
public interface ApiService {

    @GET("rss.aspx")
    Call<RssFeedResponse> getResponse();
}
