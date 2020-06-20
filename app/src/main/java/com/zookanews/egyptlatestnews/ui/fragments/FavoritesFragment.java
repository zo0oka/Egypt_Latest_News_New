package com.zookanews.egyptlatestnews.ui.fragments;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.databinding.FragmentFavoritesBinding;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.ui.adapters.ArticlePagedRecyclerAdapter;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;


public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private ArticleViewModel viewModel;
    private Article deletedItem;
    private Drawable icon;
    private ColorDrawable background;
    private ArticlePagedRecyclerAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete_white_36);
        background = new ColorDrawable(getResources().getColor(R.color.secondaryColor));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        deletedItem = adapter.getItemAtPosition(position);
                        viewModel.updateFavoriteStatus(deletedItem.getId(), false);
                        showUndoSnackBar();
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                        View itemView = viewHolder.itemView;
                        int backgroundCornerOffset = 20; //so background is behind the rounded corners of itemView

                        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                        int iconBottom = iconTop + icon.getIntrinsicHeight();

                        if (dX > 0) { // Swiping to the right
                            int iconLeft = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
                            int iconRight = itemView.getLeft() + iconMargin;
                            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                            background.setBounds(itemView.getLeft(), itemView.getTop(),
                                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
                        } else if (dX < 0) { // Swiping to the left
                            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                            int iconRight = itemView.getRight() - iconMargin;
                            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
                        } else { // view is unSwiped
                            icon.setBounds(0, 0, 0, 0);
                            background.setBounds(0, 0, 0, 0);
                        }

                        background.draw(c);
                        icon.draw(c);
                    }
                });

        binding.favoritesRecyclerView.setHasFixedSize(true);
        adapter = new ArticlePagedRecyclerAdapter(requireActivity());
        binding.favoritesRecyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(binding.favoritesRecyclerView);

        viewModel.getFavoriteArticles().observe(getViewLifecycleOwner(), articles -> {
            Timber.d("Articles Count: %s", articles.size());
            adapter.submitList(articles);
        });
    }

    private void showUndoSnackBar() {
        View view = binding.getRoot();
        Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text,
                Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snack_bar_undo, v -> viewModel.updateFavoriteStatus(deletedItem.getId(), true));
        snackbar.show();
    }
}