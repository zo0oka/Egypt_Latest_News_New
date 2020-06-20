package com.zookanews.egyptlatestnews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.helper.DataManager;
import com.zookanews.egyptlatestnews.helper.LanguageManager;
import com.zookanews.egyptlatestnews.model.Category;
import com.zookanews.egyptlatestnews.model.Website;
import com.zookanews.egyptlatestnews.ui.fragments.CategoryArticlesFragment;
import com.zookanews.egyptlatestnews.ui.fragments.NewsArticleDetailsFragment;
import com.zookanews.egyptlatestnews.ui.fragments.SearchResultFragment;
import com.zookanews.egyptlatestnews.ui.fragments.WebViewFragment;
import com.zookanews.egyptlatestnews.ui.fragments.WebsiteArticlesFragment;
import com.zookanews.egyptlatestnews.viewModels.ArticleViewModel;
import com.zookanews.egyptlatestnews.worker.DbSyncWorker;

import timber.log.Timber;

import static com.zookanews.egyptlatestnews.helper.Constants.PRIVACY_POLICY_LINK;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ArticleViewModel articleViewModel;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.updateLocale(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        setCounters();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homeFragment, R.id.nav_categoryArticlesFragment, R.id.nav_websiteArticlesFragment,
                R.id.nav_favoritesFragment)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        if (getIntent() != null) {
            if (getIntent().getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0) != 0) {
                Timber.d("Intent Article: " + getIntent().getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0));
                Bundle args = new Bundle();
                args.putInt(NewsArticleDetailsFragment.ARTICLE_ID, (int) getIntent().getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0));
                navController.navigate(R.id.nav_newsArticleDetailsFragment, args,
                        new NavOptions.Builder()
                                .setPopUpTo(R.id.nav_homeFragment, false)
                                .setLaunchSingleTop(true)
                                .build());
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            if (intent.getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0) != 0) {
                Timber.d("New Intent Article: " + intent.getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0));
                Bundle args = new Bundle();
                args.putInt(NewsArticleDetailsFragment.ARTICLE_ID, (int) intent.getLongExtra(DbSyncWorker.NOTIFICATION_ARTICLE_ID, 0));
                navController.navigate(R.id.nav_newsArticleDetailsFragment, args,
                        new NavOptions.Builder()
                                .setPopUpTo(R.id.nav_homeFragment, false)
                                .setLaunchSingleTop(true)
                                .build());
            }
        }
        super.onNewIntent(intent);
    }

    private void setCounters() {
        setWebsiteCounters();
        setCategoryCounters();
    }

    private void setWebsiteCounters() {
        for (Website website : DataManager.websites) {
            Timber.d("Website: " + website.getName() + " Menu ID: " + website.getMenuId());
            getWebsiteArticlesCount(website.getName(), website.getMenuId());
        }
    }

    private void getWebsiteArticlesCount(String websiteName, final int menuId) {
        articleViewModel.getCountOfWebsiteUnreadArticles(websiteName).observe(this, integer -> {
            Timber.d("Website name: " + websiteName + " -> Unread Articles: " + integer);
            TextView counter = (TextView) navigationView.getMenu().findItem(menuId).getActionView();
            counter.setText(String.valueOf(integer));
            counter.setGravity(Gravity.CENTER_VERTICAL);
        });
    }

    private void setCategoryCounters() {
        for (Category category : DataManager.categories) {
            getCategoryArticlesCount(category.getName(), category.getMenuId());
        }
    }

    private void getCategoryArticlesCount(String categoryName, final int menuId) {
        articleViewModel.getCountOfCategoryUnreadArticles(categoryName).observe(this, integer -> {
            Timber.d("Category name: " + categoryName + " -> Unread Articles: " + integer);
            TextView counter = (TextView) navigationView.getMenu().findItem(menuId).getActionView();
            counter.setText(String.valueOf(integer));
            counter.setGravity(Gravity.CENTER_VERTICAL);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_latest_news:
                navController.navigate(R.id.nav_homeFragment, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
                break;
            case R.id.nav_corona:
                openCategory(Category.CategoryNames.CORONA);
                break;
            case R.id.nav_politics:
                openCategory(Category.CategoryNames.POLITICS);
                break;
            case R.id.nav_accidents:
                openCategory(Category.CategoryNames.ACCIDENTS);
                break;
            case R.id.nav_finance:
                openCategory(Category.CategoryNames.FINANCE);
                break;
            case R.id.nav_sports:
                openCategory(Category.CategoryNames.SPORTS);
                break;
            case R.id.nav_woman:
                openCategory(Category.CategoryNames.WOMAN);
                break;
            case R.id.nav_arts:
                openCategory(Category.CategoryNames.ARTS);
                break;
            case R.id.nav_technology:
                openCategory(Category.CategoryNames.TECHNOLOGY);
                break;
            case R.id.nav_videos:
                openCategory(Category.CategoryNames.VIDEOS);
                break;
            case R.id.nav_automotive:
                openCategory(Category.CategoryNames.AUTOMOTIVE);
                break;
            case R.id.nav_investigations:
                openCategory(Category.CategoryNames.INVESTIGATIONS);
                break;
            case R.id.nav_culture:
                openCategory(Category.CategoryNames.CULTURE);
                break;
            case R.id.nav_travel:
                openCategory(Category.CategoryNames.TRAVEL);
                break;
            case R.id.nav_health:
                openCategory(Category.CategoryNames.HEALTH);
                break;
            case R.id.nav_opinion:
                openCategory(Category.CategoryNames.OPINION);
                break;
            case R.id.nav_world_arab:
                openCategory(Category.CategoryNames.WORLD_ARAB);
                break;
            case R.id.nav_fashion:
                openCategory(Category.CategoryNames.FASHION);
                break;
            case R.id.nav_almasry_alyoum:
                openWebsite(Website.WebsiteNames.ALMASRY_ALYOUM);
                break;
            case R.id.nav_alwatan:
                openWebsite(Website.WebsiteNames.ALWATAN);
                break;
            case R.id.nav_aldostour:
                openWebsite(Website.WebsiteNames.ALDOSTOUR);
                break;
            case R.id.nav_akhbarak:
                openWebsite(Website.WebsiteNames.AKHBARAK);
                break;
            case R.id.nav_alwafd:
                openWebsite(Website.WebsiteNames.ALWAFD);
                break;
            case R.id.nav_bbc_arabic:
                openWebsite(Website.WebsiteNames.BBC_ARABIA);
                break;
            case R.id.nav_alfagr:
                openWebsite(Website.WebsiteNames.ALFAGR);
                break;
            case R.id.nav_rose_alyousef:
                openWebsite(Website.WebsiteNames.ROSE_ALYOUSEF);
                break;
            case R.id.nav_akhbar_alyoum:
                openWebsite(Website.WebsiteNames.AKHBAR_ELYOUM);
                break;
            case R.id.nav_sada_elbalad:
                openWebsite(Website.WebsiteNames.SADA_ELBALAD);
                break;
            case R.id.nav_bawabet_veto:
                openWebsite(Website.WebsiteNames.BAWABET_VETO);
                break;
            case R.id.nav_almogaz:
                openWebsite(Website.WebsiteNames.ALMOGAZ);
                break;
            case R.id.nav_alyoum7:
                openWebsite(Website.WebsiteNames.ALYOUM7);
                break;
            case R.id.nav_alshorouk:
                openWebsite(Website.WebsiteNames.AL_SHOROUK);
                break;
            case R.id.nav_arab_finance:
                openWebsite(Website.WebsiteNames.ARAB_FINANCE);
                break;
            case R.id.nav_privacy_policy:
                openLink(PRIVACY_POLICY_LINK);
                break;
            default:
                navController.navigate(id);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openLink(String link) {
        Bundle args = new Bundle();
        args.putString(WebViewFragment.LINK, link);
        navController.navigate(R.id.nav_webViewFragment, args);
    }


    private void openCategory(String categoryName) {
        Bundle args = new Bundle();
        args.putString(CategoryArticlesFragment.CATEGORY_NAME, categoryName);
        navController.navigate(R.id.nav_categoryArticlesFragment, args, new NavOptions.Builder().setLaunchSingleTop(true).build());
    }

    private void openWebsite(String websiteName) {
        Bundle args = new Bundle();
        args.putString(WebsiteArticlesFragment.WEBSITE_NAME, websiteName);
        navController.navigate(R.id.nav_websiteArticlesFragment, args, new NavOptions.Builder().setLaunchSingleTop(true).build());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle args = new Bundle();
        args.putString(SearchResultFragment.SEARCH_QUERY, query);
        navController.navigate(R.id.nav_searchResultFragment, args, new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(R.id.nav_homeFragment, false)
                .build());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}