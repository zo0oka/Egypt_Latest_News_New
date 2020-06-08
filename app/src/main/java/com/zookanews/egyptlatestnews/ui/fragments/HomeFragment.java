package com.zookanews.egyptlatestnews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zookanews.egyptlatestnews.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import timber.log.Timber;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d("onViewCreated");

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                SaxXmlParser.parse("https://alwafd.news/feed/21");
//                XmlParser parser = new XmlParser();
//                try {
//                    List<Item> items = parser.init("https://www.elwatannews.com/home/rssfeeds");
//                    Timber.d("Items: " + new Gson().toJson(items));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Timber.d(e.getMessage());
//                } catch (XmlPullParserException e) {
//                    e.printStackTrace();
//                    Timber.d(e.getMessage());
//                }
            }
        });
    }
}