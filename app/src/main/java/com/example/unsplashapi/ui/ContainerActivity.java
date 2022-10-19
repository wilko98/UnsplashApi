package com.example.unsplashapi.ui;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.unsplashapi.R;
import com.example.unsplashapi.common.SingleFragmentActivity;
import com.example.unsplashapi.ui.SearchFragment.SearchFragment;

public class ContainerActivity extends SingleFragmentActivity {

    public DownloadManager downloadManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        downloadManager = downloadManager = (DownloadManager) getSystemService(getApplication().DOWNLOAD_SERVICE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return SearchFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                changeFragment(SearchFragment.newInstance());
                break;
            case R.id.action_random:
                changeFragment(RandomPhotoFragment.newInstance());
                    break;
        }
        return false;
    }

}
