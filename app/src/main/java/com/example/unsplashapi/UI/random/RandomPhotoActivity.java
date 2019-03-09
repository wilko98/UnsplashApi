package com.example.unsplashapi.UI.random;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.unsplashapi.R;
import com.example.unsplashapi.UI.Search.SearchFragment;
import com.example.unsplashapi.common.SingleFragmentActivity;
import com.ferfalk.simplesearchview.SimpleSearchView;

public class RandomPhotoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return RandomPhotoFragment.newInstance();
    }

    protected void openSearchFragment(){
        changeFragment(SearchFragment.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                
                openSearchFragment();
                break;
        }
        return false;
    }
}
