package com.example.chopsfull_.test_task_tereshchuk.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private String[] PAGE_TITLES;
    private Fragment[] PAGES;

    public MyPagerAdapter(FragmentManager fragmentManager,String[] PAGE_TITLES,Fragment[] PAGES) {
        super(fragmentManager);
        this.PAGE_TITLES = PAGE_TITLES;
        this.PAGES = PAGES;
    }

    /* PagerAdapter for supplying the ViewPager with the pages (fragments) to display. */
    @Override
    public Fragment getItem(int position) {
        return PAGES[position];
    }

    @Override
    public int getCount() {
        return PAGES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }
}
