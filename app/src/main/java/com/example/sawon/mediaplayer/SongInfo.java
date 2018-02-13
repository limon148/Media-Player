package com.example.sawon.mediaplayer;

/**
 * Created by sawon on 2/13/2018.
 */

public class SongInfo {
    String title,location;

    public SongInfo() {
    }

    public SongInfo(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
