package com.example.unsplashapi.data.api;

import com.example.unsplashapi.data.model.Image;
import com.example.unsplashapi.data.model.RandomPhotoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UnsplashApi {
    @GET("/photos/random")
    Single<Image> getRandomPhoto();

}
