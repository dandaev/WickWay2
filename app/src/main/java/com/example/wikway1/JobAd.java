package com.example.wikway1;

public class JobAd {
    private String title;
    private String imageLink;
    private String bundesland;
    private boolean fav;
    public JobAd(String title,String imageLink, String bundesland){
        this.title=title;
        this.imageLink = imageLink;
        this.bundesland = bundesland;
        fav = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
