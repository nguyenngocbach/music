package com.bkav.appmusic.model;

import java.io.Serializable;

public class Song implements Serializable  {
    private String path;
    private String title;
    private String author;
    private String duration;
    private boolean isPlay=false;

    public Song(String path, String title, String author, String duration) {
        this.path = path;
        this.title = title;
        this.author = author;
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
