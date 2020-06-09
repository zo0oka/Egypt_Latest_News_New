package com.zookanews.egyptlatestnews.remote;

import com.zookanews.egyptlatestnews.interfaces.NewsListener;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.model.Feed;
import com.zookanews.egyptlatestnews.parser.SaxXmlHandler;

import org.jetbrains.annotations.NotNull;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

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

    public static void loadNews(ApiService service, List<Feed> feeds, NewsListener listener) {
        for (Feed feed : feeds) {
            Timber.d("Loading Feed: %s", feed.getRssLink());
            service.getResponse(feed.getRssLink()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                    Timber.d("onResponse...");
                    Timber.d("Response Code: %s", response.code());
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            listener.onNewsSuccess(parseNewsArticles(response.body(), feed.getCategoryName(), feed.getWebsiteName()));
                        } else {
                            listener.onNewsSuccess(Collections.emptyList());
                        }
                    } else {
                        String errorString = null;
                        try {
                            if (response.errorBody() != null) {
                                errorString = response.errorBody().string();
                            } else {
                                errorString = "Unknown Error.";
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Timber.d("News Error: %s", errorString);
                        listener.onNewsError(errorString);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                    Timber.d("News Failure: %s", t.getMessage());
                    listener.onNewsFailure(t.getMessage());
                }
            });
        }
    }

    private static List<Article> parseNewsArticles(ResponseBody response, String categoryName, String websiteName) {
        List<Article> articles = new ArrayList<>();
        XMLReader xmlReader;
        InputStream inputStream = response.byteStream();
        try {
            xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            SaxXmlHandler saxHandler = new SaxXmlHandler(categoryName, websiteName);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(inputStream));
            articles = saxHandler.getArticles();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
