package com.zookanews.egyptlatestnews.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.helper.Constants;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.ui.viewHolders.ArticleViewHolder;
import com.zookanews.egyptlatestnews.ui.viewHolders.BannerAdsViewHolder;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 09 Jun, 2020.
 * Have a nice day!
 */
public class ArticlePagedRecyclerAdapter extends PagedListAdapter<Article, RecyclerView.ViewHolder> {

    private static final int ARTICLE = 0;
    private static final int BANNER = 1;
    private FragmentActivity activity;

    public ArticlePagedRecyclerAdapter(FragmentActivity activity) {
        super(Article.DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerAdsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_banner_ad, parent, false));
            case ARTICLE:
            default:
                return new ArticleViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_article_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ARTICLE:
                ((ArticleViewHolder) holder).bindTo(getItem(position), activity);
                break;
            case BANNER:
                BannerAdsViewHolder bannerHolder = (BannerAdsViewHolder) holder;
                AdView adView = new AdView(activity);
                adView.setAdSize(AdSize.LARGE_BANNER);
                adView.setAdUnitId(Constants.ADMOB_BANNER_UNIT_ID);
                adView.loadAd(new AdRequest.Builder().build());
                ViewGroup adCardView = bannerHolder.binding.adCardView;
                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 6 == 0) ? BANNER : ARTICLE;
    }

    public Article getItemAtPosition(int position) {
        return getItem(position);
    }
}
