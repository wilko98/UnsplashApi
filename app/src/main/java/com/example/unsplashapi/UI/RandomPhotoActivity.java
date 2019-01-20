package com.example.unsplashapi.UI;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.unsplashapi.R;
import com.example.unsplashapi.common.SingleFragmentActivity;

public class RandomPhotoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return RandomPhotoFragment.newInstance();
    }
}
