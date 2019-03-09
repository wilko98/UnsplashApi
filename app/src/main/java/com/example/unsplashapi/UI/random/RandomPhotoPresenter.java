package com.example.unsplashapi.UI.random;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.arellomobile.mvp.InjectViewState;
import com.example.unsplashapi.common.BasePresenter;
import com.example.unsplashapi.utils.ApiUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RandomPhotoPresenter extends BasePresenter<RandomPhotoView> {

    private DownloadManager downloadManager;
    private long refid;

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
    public void downloadFile(DownloadManager downloadManager, String URL){
        Uri Download_Uri = Uri.parse(URL);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("GadgetSaint Downloading " + "Sample" + ".png");
        request.setDescription("Downloading " + "Sample" + ".png");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/GadgetSaint/"  + "/" + "Sample" + ".png");
        refid = downloadManager.enqueue(request);
    }

}
