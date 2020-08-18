package com.bkav.appmusic.until;

import android.os.Bundle;

import com.bkav.appmusic.model.Song;

public class Coast {
   public static final String TITLE_SONG="title_song";
   public static final String AUTHOR_SONG="author_song";

   public static Bundle getSongFormat(Song song){
       Bundle bundle= new Bundle();
       bundle.putString(TITLE_SONG,song.getTitle());
       bundle.putString(AUTHOR_SONG,song.getAuthor());
       return bundle;
   }
}
