package com.example.unsplashapi.data.model;

public class RandomPhotoResponse {

    /**
     * urls : {"raw":"https://images.unsplash.com/1/type-away.jpg","full":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg","regular":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=1080&fit=max","small":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=400&fit=max","thumb":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=200&fit=max"}
     */

    public UrlsBean urls;

    public String getUrls() {
        return urls.getRegular();
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }
}
