package com.example.unsplashapi.UI;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.unsplashapi.common.BasePresenter;
import com.example.unsplashapi.utils.ApiUtils;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RandomPhotoPresenter extends BasePresenter<RandomPhotoView> {

    @SuppressLint("CheckResult")
    public void getRandomPhoto() {
        ApiUtils.getApiService().getRandomPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    getViewState().showError();
                    return null;
                })
                .subscribe(
                        randomPhotoResponse ->{
                            getViewState().showRandomPhoto(randomPhotoResponse.getStandartImageUrl());
                        },
                        throwable -> getViewState().showError()
                );
    }


}
