package com.example.chopsfull_.test_task_tereshchuk;

import android.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.chopsfull_.test_task_tereshchuk.fragments.FeaturedFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.NewFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.FeedFragment;
import com.example.chopsfull_.test_task_tereshchuk.utils.singletone.LoginCredentials;
import com.example.chopsfull_.test_task_tereshchuk.utils.tabUtils;

public class MainActivity extends tabUtils {

    LoginCredentials credentials = LoginCredentials.getInstance();

    Fragment featuredFragment = FeaturedFragment.getInstance();
    Fragment newFragment = NewFragment.getInstance();
    Fragment feedFragment = FeedFragment.getInstance();

    // Titles of the individual pages (displayed in tabs)
    private final String[] PAGE_TITLES = new String[] {
            "FEATURED",
            "NEW",
            "FEED"
    };

    // The fragments that are used as the individual pages
    private final Fragment[] PAGES = new Fragment[] {
            FeaturedFragment.getInstance(),
            NewFragment.getInstance(),
            FeedFragment.getInstance()
    };

    @Override
    protected String[] getPageTitles() {
        return PAGE_TITLES;
    }

    @Override
    protected Fragment[] getPages() {
        return PAGES;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_log_out,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(credentials.isLoggedIn()) {
            menu.getItem(R.id.menu_log_out).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_log_out:{
                item.setVisible(false);
            }
            default: return super.onOptionsItemSelected(item);
        }
    }
}





