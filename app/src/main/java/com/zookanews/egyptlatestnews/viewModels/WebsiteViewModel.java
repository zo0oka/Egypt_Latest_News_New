package com.zookanews.egyptlatestnews.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zookanews.egyptlatestnews.db.repos.WebsiteRepo;
import com.zookanews.egyptlatestnews.model.Website;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 17 Jun, 2020.
 * Have a nice day!
 */
public class WebsiteViewModel extends AndroidViewModel {

    private WebsiteRepo websiteRepository;

    public WebsiteViewModel(@NonNull Application application) {
        super(application);
        websiteRepository = new WebsiteRepo(application);
    }

    public LiveData<Website> getWebsiteByName(String websiteName) {
        return websiteRepository.getWebsiteByName(websiteName);
    }
}