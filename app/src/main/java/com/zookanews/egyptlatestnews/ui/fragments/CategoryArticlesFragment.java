package com.zookanews.egyptlatestnews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentCategoryArticlesBinding;
import com.zookanews.egyptlatestnews.ui.adapters.ArticlePagedRecyclerAdapter;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;
import com.zookanews.egyptlatestnews.viewModels.CategoryViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;


public class CategoryArticlesFragment extends Fragment {

    public static final String CATEGORY_NAME = "category_name";
    private FragmentCategoryArticlesBinding binding;
    private ArticleViewModel articleViewModel;
    private CategoryViewModel categoryViewModel;
    private String categoryName;

    public CategoryArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getString(CATEGORY_NAME) != null) {
                categoryName = getArguments().getString(CATEGORY_NAME);
                Timber.d("Category Name: %s", categoryName);
            }
        }

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_articles, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articleViewModel.setCategoryName(categoryName);

        categoryViewModel.getCategoryByName(categoryName).observe(getViewLifecycleOwner(), category -> {
            Timber.d("Category: " + category.getTitle());
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(category.getTitle());
        });

        binding.categoryArticlesRecyclerView.setHasFixedSize(true);
        ArticlePagedRecyclerAdapter adapter = new ArticlePagedRecyclerAdapter(requireActivity());
        binding.categoryArticlesRecyclerView.setAdapter(adapter);

        articleViewModel.getCategoryArticles().observe(getViewLifecycleOwner(), articles -> {
            Timber.d("Articles Count: %s", articles.size());
            adapter.submitList(articles);
        });
    }
}