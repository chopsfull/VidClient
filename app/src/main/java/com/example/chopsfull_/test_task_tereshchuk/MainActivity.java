package com.example.chopsfull_.test_task_tereshchuk;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chopsfull_.test_task_tereshchuk.adapters.MyPagerAdapter;
import com.example.chopsfull_.test_task_tereshchuk.fragments.ContainerFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.FeaturedFragment;
import com.example.chopsfull_.test_task_tereshchuk.fragments.NewFragment;
import com.example.chopsfull_.test_task_tereshchuk.utils.singletone.LoginCredentials;
import com.example.chopsfull_.test_task_tereshchuk.utils.tabUtils;

public class MainActivity extends tabUtils {

    LoginCredentials credentials = LoginCredentials.getInstance();



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
            new ContainerFragment()
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
            menu.findItem(R.id.menu_log_out).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_log_out:{
                item.setVisible(false);
                credentials.reset();
                clearPrefs();
                mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),PAGE_TITLES,
                        new Fragment[] {
                        FeaturedFragment.getInstance(),
                        NewFragment.getInstance(),
                        new ContainerFragment()
                }));
                mViewPager.setCurrentItem(1);
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void clearPrefs(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().remove(getString(R.string.saved_credentials)).commit();
    }

    private void saveCreds() {
        if(credentials.isLoggedIn()) {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.saved_credentials), getCreds());
            editor.commit();
        }
    }

    private String getCreds(){
        if(credentials.getLogin()!=null&&credentials.getPassword()!=null)
            return credentials.getLogin()+":"+credentials.getPassword();
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveCreds();
    }
}





