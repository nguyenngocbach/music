package com.bkav.appmusic.service;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.bkav.appmusic.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private List<Song> mSongs;
    private MediaPlayer mPlayer;
    private int currentSong=0;

    private Context mContext;
    public MusicManager(Context context){
        mContext=context;
        getAllSong();
        mPlayer= new MediaPlayer();
    }

    public void onPlay(){
        try {
            mPlayer.setDataSource(mSongs.get(currentSong).getPath());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public


    public int getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(int currentSong) {
        this.currentSong = currentSong;
    }

    public List<Song> getSong(){
        return mSongs;
    }


    private void getAllSong() {
        String[] allColoumSong= new String[]{
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION
        };

        Cursor cursor= mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,allColoumSong,null,null,null,null);
        cursor.moveToFirst();
        mSongs= new ArrayList<>();
        while (cursor!=null){
            String path= cursor.getColumnName(0);
            String title= cursor.getColumnName(1);
            String author= cursor.getColumnName(2);
            String duration= cursor.getColumnName(3);
            Song song= new Song(path,title,author,duration);
            mSongs.add(song);
            cursor.moveToNext();
        }
        cursor.close();
    }

}
