package com.example.unsplashapi.data.model;

public class UrlsBean {
    /**
     * raw : https://images.unsplash.com/1/type-away.jpg
     * full : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg
     * regular : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=1080&fit=max
     * small : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=400&fit=max
     * thumb : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=200&fit=max
     */

    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
