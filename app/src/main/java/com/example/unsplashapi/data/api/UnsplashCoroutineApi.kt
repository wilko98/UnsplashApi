package com.example.unsplashapi.data.api

import com.example.unsplashapi.data.model.Image
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface UnsplashCoroutineApi {
    @GET("/photos/random")
    fun getUser(): Deferred<Image>
}