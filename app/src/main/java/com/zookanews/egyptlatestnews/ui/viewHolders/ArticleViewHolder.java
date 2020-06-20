package com.zookanews.egyptlatestnews.ui.viewHolders;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.ItemArticleListBinding;
import com.zookanews.egyptlatestnews.interfaces.ClickHandlers;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.ui.fragments.NewsArticleDetailsFragment;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 09 Jun, 2020.
 * Have a nice day!
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder implements ClickHandlers.NewsArticleList {

    private ItemArticleListBinding binding;
    private FragmentActivity activity;

    public ArticleViewHolder(@NonNull ItemArticleListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindTo(Article article, FragmentActivity activity) {
        this.activity = activity;
        if (article != null) {
            binding.setArticle(article);
            binding.setHandler(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.descriptionTextView.setText(Html.fromHtml(article.getDescription(), Html.FROM_HTML_MODE_LEGACY).toString());
            } else {
                binding.descriptionTextView.setText(Html.fromHtml(article.getDescription()).toString());
            }
        }
    }

    @Override
    public void onArticleClick(View view, int articleId) {
        Bundle args = new Bundle();
        args.putInt(NewsArticleDetailsFragment.ARTICLE_ID, articleId);
        Navigation.findNavController(view).navigate(R.id.nav_newsArticleDetailsFragment, args);
    }

    @Override
    public void onFavorite(int articleId, boolean isFavorite) {
        new ViewModelProvider(activity).get(ArticleViewModel.class).updateFavoriteStatus(articleId, !isFavorite);
    }
}
