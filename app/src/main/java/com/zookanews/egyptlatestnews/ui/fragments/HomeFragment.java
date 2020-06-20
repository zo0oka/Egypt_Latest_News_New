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
import androidx.paging.PagedList;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentHomeBinding;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.ui.adapters.ArticlePagedRecyclerAdapter;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArticleViewModel viewModel;
    private ArticlePagedRecyclerAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        Timber.d("onCreate");
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        Timber.d("onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Timber.d("onViewCreated");

        binding.homeProgress.setVisibility(View.VISIBLE);

        binding.homeRecyclerView.setHasFixedSize(true);
        adapter = new ArticlePagedRecyclerAdapter(requireActivity());
        binding.homeRecyclerView.setAdapter(adapter);

        viewModel.getArticles().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void updateUI(PagedList<Article> articles) {
        Timber.d("Articles Count: %s", articles.size());
        adapter.submitList(articles);
        binding.homeProgress.setVisibility(View.GONE);
    }
}