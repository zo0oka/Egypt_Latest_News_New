package com.zookanews.egyptlatestnews.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 06 Jun, 2020.
 * Have a nice day!
 */
public interface ApiService {
    @GET
    Call<ResponseBody> getResponse(@Url String url);
}
