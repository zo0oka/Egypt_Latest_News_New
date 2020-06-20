package com.zookanews.egyptlatestnews.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.ads.AdRequest;
import com.google.gson.Gson;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentNewsArticleDetailsBinding;
import com.zookanews.egyptlatestnews.interfaces.ClickHandlers;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import timber.log.Timber;


public class NewsArticleDetailsFragment extends Fragment implements ClickHandlers.NewsArticle {

    public static final String ARTICLE_ID = "article_id";
    private FragmentNewsArticleDetailsBinding binding;
    private NavController navController;
    private ArticleViewModel viewModel;
    private int articleId;
    private boolean isFavorite;
    private Article newsArticle;

    public NewsArticleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            articleId = getArguments().getInt(ARTICLE_ID);
        }
        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_article_details, container, false);
        return binding.getRoot();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Timber.d("onViewCreated");

        Timber.d("Article ID: %s", articleId);
        binding.articleDetailsProgress.setVisibility(View.VISIBLE);
        binding.setHandler(this);
        binding.topArticleAdView.loadAd(new AdRequest.Builder().build());
        binding.bottomArticleAdView.loadAd(new AdRequest.Builder().build());

        viewModel.getArticleByIdLiveData(articleId).observe(getViewLifecycleOwner(), new Observer<Article>() {
            @Override
            public void onChanged(Article article) {
                newsArticle = article;
                Timber.d(new Gson().toJson(article));
                binding.setArticle(article);
                isFavorite = article.getFavorite();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.articleDescription.setText(Html.fromHtml(article.getDescription(), Html.FROM_HTML_MODE_LEGACY).toString());
                } else {
                    binding.articleDescription.setText(Html.fromHtml(article.getDescription()).toString());
                }
                Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(article.getTitle());
                Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).invalidateOptionsMenu();
                viewModel.updateReadStatus(article.getId(), true);
                binding.articleDetailsProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onOpenWebsite(View view, String link) {
        Timber.d("Article Link: %s", link);
        Bundle args = new Bundle();
        args.putString(WebViewFragment.LINK, link);
        navController.navigate(R.id.nav_webViewFragment, args);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.news_article_menu, menu);
        menu.findItem(R.id.action_favorite).setIcon(isFavorite ? R.drawable.ic_action_favorite_checked : R.drawable.ic_action_favorite_unchecked);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share_listing) {
            Timber.d("Share Clicked");
            shareListing();
            return true;
        } else if (item.getItemId() == R.id.action_favorite) {
            setFavorite(item);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void shareListing() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, newsArticle.getTitle());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, newsArticle.getTitle() + "\n\n" + newsArticle.getDescription() + "\n\n" + newsArticle.getLink());
        sharingIntent.putExtra(Intent.EXTRA_TITLE, newsArticle.getTitle());
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void setFavorite(MenuItem item) {
        isFavorite = !isFavorite;
        item.setIcon(isFavorite ? R.drawable.ic_action_favorite_checked : R.drawable.ic_action_favorite_unchecked);
        viewModel.updateFavoriteStatus(articleId, isFavorite);
    }
}