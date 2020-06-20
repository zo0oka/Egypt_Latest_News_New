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
import com.zookanews.egyptlatestnews.databinding.FragmentWebsiteArticlesBinding;
import com.zookanews.egyptlatestnews.ui.adapters.ArticlePagedRecyclerAdapter;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;
import com.zookanews.egyptlatestnews.viewModels.WebsiteViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class WebsiteArticlesFragment extends Fragment {

    public static final String WEBSITE_NAME = "website_name";
    private FragmentWebsiteArticlesBinding binding;
    private ArticleViewModel articleViewModel;
    private WebsiteViewModel websiteViewModel;
    private String websiteName;

    public WebsiteArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getString(WEBSITE_NAME) != null) {
                websiteName = getArguments().getString(WEBSITE_NAME);
                Timber.d("Website Name: %s", websiteName);
            }
        }

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        websiteViewModel = new ViewModelProvider(this).get(WebsiteViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_website_articles, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articleViewModel.setWebsiteName(websiteName);

        websiteViewModel.getWebsiteByName(websiteName).observe(getViewLifecycleOwner(), website -> {
            Timber.d("Website: " + website.getTitle());
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(website.getTitle());
        });

        binding.websiteArticlesRecyclerView.setHasFixedSize(true);
        ArticlePagedRecyclerAdapter adapter = new ArticlePagedRecyclerAdapter(requireActivity());
        binding.websiteArticlesRecyclerView.setAdapter(adapter);

        articleViewModel.getWebsiteArticles().observe(getViewLifecycleOwner(), articles -> {
            Timber.d("Articles Count: %s", articles.size());
            adapter.submitList(articles);
        });
    }
}