package com.zookanews.egyptlatestnews.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 06 Jun, 2020.
 * Have a nice day!
 */
public class ApiClient {

    public static final String BASE_URL = "https://www.elfagr.com/";
    private static ApiService instance = null;

    public static ApiService getInstance() {

        if (instance == null) {

//            int cacheSize = 30 * 1024 * 1024; // 10 MB
//            Cache cache = new Cache(Context.getCacheDir(), cacheSize);

            // For logging
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            // Building OkHttp client
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
//                            .addHeader("Content-Type", "application/rss+xml")
//                            .addHeader("Accept", "application/rss+xml")
                            .build()))
//                    .cache(cache)
                    .callTimeout(0, TimeUnit.MILLISECONDS)
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .connectTimeout(0, TimeUnit.MILLISECONDS)
                    .build();

            // Retrofit Builder
            Retrofit.Builder builder =
                    new Retrofit
                            .Builder()
                            .client(client)
                            .baseUrl(BASE_URL);

            instance = builder.build().create(ApiService.class);
        }
        return instance;
    }
}
