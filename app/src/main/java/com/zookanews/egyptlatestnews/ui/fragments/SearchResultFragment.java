package com.zookanews.egyptlatestnews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentSearchResultBinding;
import com.zookanews.egyptlatestnews.ui.adapters.ArticlePagedRecyclerAdapter;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class SearchResultFragment extends Fragment {

    public static final String SEARCH_QUERY = "search_query";

    private String query;
    private ArticleViewModel articleViewModel;
    private FragmentSearchResultBinding binding;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(SEARCH_QUERY);
        }
        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articleViewModel.setSearchQuery(query);

        binding.searchResultRecyclerView.setHasFixedSize(true);
        ArticlePagedRecyclerAdapter adapter = new ArticlePagedRecyclerAdapter(requireActivity());
        binding.searchResultRecyclerView.setAdapter(adapter);

        articleViewModel.getSearchResults().observe(getViewLifecycleOwner(), articles -> {
            Timber.d("Articles Count: %s", articles.size());
            adapter.submitList(articles);
        });
    }
}