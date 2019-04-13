package com.example.unsplashapi.data.api;

import com.example.unsplashapi.data.model.Image;
import com.example.unsplashapi.data.model.RandomPhotoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UnsplashApi {
    @GET("/photos/random")
    Single<Image> getRandomPhoto();

    @GET("/search/photos")
    Call<List<Image>> searchPhotos();


}
