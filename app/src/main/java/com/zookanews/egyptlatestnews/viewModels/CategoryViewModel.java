package com.zookanews.egyptlatestnews.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zookanews.egyptlatestnews.db.repos.CategoryRepo;
import com.zookanews.egyptlatestnews.model.Category;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 17 Jun, 2020.
 * Have a nice day!
 */
public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepo categoryRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepo(application);
    }

    public LiveData<Category> getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}
