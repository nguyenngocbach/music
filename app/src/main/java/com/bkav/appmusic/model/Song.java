package com.bkav.appmusic.model;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String author;
    private long timeTotal;
    private boolean isPlay=false;

    public Song(String title, String author, long timeTotal, boolean isPlay) {
        this.title = title;
        this.author = author;
        this.timeTotal = timeTotal;
        this.isPlay = isPlay;
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

    public long getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(long timeTotal) {
        this.timeTotal = timeTotal;
    }
}
