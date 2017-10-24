package com.example.chopsfull_.test_task_tereshchuk;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.chopsfull_.test_task_tereshchuk.fragments.Page1Fragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.Page2Fragment;
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
            new Page1Fragment(),
            new Page2Fragment(),
            new FeedFragment()
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





