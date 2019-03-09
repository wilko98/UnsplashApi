package com.example.unsplashapi.UI.random;

import com.example.unsplashapi.common.BaseView;

import io.reactivex.annotations.NonNull;

public interface RandomPhotoView extends BaseView {


    void showStart(@NonNull String text);

    void showRandomPhoto(@NonNull String standartImageUrl);

}
