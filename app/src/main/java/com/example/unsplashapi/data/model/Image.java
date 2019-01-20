package com.example.unsplashapi.data.model;

public class Image {

    /**
     * id : LBI7cgq3pbM
     * width : 5245
     * height : 3497
     * color : #60544D
     * urls : {"raw":"https://images.unsplash.com/example.jpg?ixid=123123123","full":"https://images.unsplash.com/example.jpg?q=75&fm=jpg&ixid=123123123","regular":"https://images.unsplash.com/example.jpg?q=75&fm=jpg&w=1080&fit=max&?ixid=123123123","small":"https://images.unsplash.com/example.jpg?q=75&fm=jpg&w=400&fit=max&&ixid=123123123","thumb":"https://images.unsplash.com/example.jpg?q=75&fm=jpg&w=200&fit=max&ixid=123123123"}
     * user : {}
     * links : {}
     */

    private String id;
    private int width;
    private int height;
    private String color;
    private UrlsBean urls;
    private UserBean user;
    private LinksBean links;

    public String getStandartImageUrl(){
        return urls.getRegular();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }
}
