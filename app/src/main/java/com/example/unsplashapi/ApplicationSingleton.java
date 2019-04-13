package com.example.unsplashapi;

import android.app.Application;

import com.squareup.picasso.Picasso;

public class ApplicationSingleton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso;
        picasso = new Picasso.Builder(this).build();
        Picasso.setSingletonInstance(picasso);

    }

}
