package com.example.chopsfull_.test_task_tereshchuk;

import android.app.Fragment;

import com.example.chopsfull_.test_task_tereshchuk.fragments.FeaturedFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.NewFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.FeedFragment;
import com.example.chopsfull_.test_task_tereshchuk.utils.tabUtils;

public class MainActivity extends tabUtils {
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
}





