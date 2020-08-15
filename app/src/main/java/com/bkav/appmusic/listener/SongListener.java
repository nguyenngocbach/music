package com.bkav.appmusic.listener;

import com.bkav.appmusic.model.Song;

public interface SongListener {
    void selectMusic(Song song, int position);
}
