package com.zookanews.egyptlatestnews.ui.viewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zookanews.egyptlatestnews.databinding.ItemBannerAdBinding;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 3/27/20.
 * Have a nice day!
 */
public class BannerAdsViewHolder extends RecyclerView.ViewHolder {

    public ItemBannerAdBinding binding;

    public BannerAdsViewHolder(@NonNull ItemBannerAdBinding binding) {
        super(binding.getRoot());
        Timber.d("Loading Ad");
        this.binding = binding;
    }
}
