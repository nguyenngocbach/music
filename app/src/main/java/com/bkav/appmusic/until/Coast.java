package com.bkav.appmusic.until;

import android.os.Bundle;

import com.bkav.appmusic.model.Song;

public class Coast {
   public static final String TITLE_SONG="title_song";
   public static final String AUTHOR_SONG="author_song";
   public static final String IS_PLAY="com.bkav.appmusic.until.isPlay";

   public static Bundle getSongFormat(Song song){
       Bundle bundle= new Bundle();
       bundle.putString(TITLE_SONG,song.getTitle());
       bundle.putString(AUTHOR_SONG,song.getAuthor());
       bundle.putBoolean(IS_PLAY,song.isPlay());
       return bundle;
   }
}
