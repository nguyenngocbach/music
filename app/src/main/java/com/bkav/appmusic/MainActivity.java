package com.bkav.appmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bkav.appmusic.fragment.AllSongFragment;
import com.bkav.appmusic.fragment.MediaPlaybackFragment;
import com.bkav.appmusic.fragment.ToolbarFragment;
import com.bkav.appmusic.listener.SongListener;
import com.bkav.appmusic.model.Song;
import com.bkav.appmusic.service.MusicService;
import com.bkav.appmusic.until.Coast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SongListener, MediaPlaybackFragment.MediaPlayFragmentListenner, AllSongFragment.ShowMeDiaPlayListener {
    private static final String KEY_SONG = "com.bkav.appmusic.MainActivity.Song";
    private static final String KEY_SONG_POSITION = "com.bkav.appmusic.MainActivity.Position";
    private static final int MY_PERMISSION_REQUEST = 123;
    private static final String KEY_ALL_SONG = "com.bkav.appmusic.MainActivity.ALLSong";

    private FragmentManager fragmentManager;
    public boolean isVertical = false;
    public FragmentTransaction fplay;
    private Song song = null;
    private int position = -1;

    private boolean check = false;
    private boolean isConnection = false;
    private MusicService musicService;
    private List<Song> mSongs;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.LocalMusic binder = (MusicService.LocalMusic) iBinder;
            isConnection = true;
            musicService = binder.getInstanceService();
            Log.d("bachdz", "Successful");
            AllSongFragment mf = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
            mf.addData(musicService.getMusicManager().getmSongs());
            mf.setMusicManager(musicService.getMusicManager());
            mf.isPlayMusic(musicService.getMusicManager().isMusicPlaying());
            if (check){
                mf.setPosition(position);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnection = false;
            musicService = null;
            Log.d("bachdz", "faill");
        }
    };

    public boolean isIsVertical() {
        return isVertical;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            check = true;
            song = (Song) savedInstanceState.getSerializable(KEY_SONG);
            position = savedInstanceState.getInt(KEY_SONG_POSITION);
            Log.d("bach", "savedInstanceState");
            Log.d("bach", song.getTitle() + "");
            Log.d("bach", song.getAuthor());
            mSongs = (List<Song>) savedInstanceState.getSerializable(KEY_ALL_SONG);

        }

        if (findViewById(R.id.vertical_Screen) != null)
            isVertical = true;

        if (findViewById(R.id.vertical_Screen) != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.allSongFragment, new AllSongFragment());
            ft.commit();
        } else {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.allSongFragment, new AllSongFragment());
            ft.commit();

            fplay = fragmentManager.beginTransaction();
            fplay.replace(R.id.musicPlayer, new MediaPlaybackFragment());
            fplay.addToBackStack(null);
            fplay.commit();
        }

        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        // ????????????????????????
//        if (musicService!=null){
//            AllSongFragment mf= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
//            Log.d("bachdz","All Song");
//            mf.addData(musicService.getMusicManager().getmSongs());
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AllSongFragment mf = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        if (check == true) {
//            mf.addData(mSongs);
//            Log.d("bachdz","hello");
//            mf.setPosition(position);
            if (!isVertical) {
                MediaPlaybackFragment mPlay = (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
                mPlay.setDataMusic(song);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("bachdz","onResume");
    }

    @Override
    public void onLike() {
        // todo songthing
//        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
//        ft.onLike();

    }

    @Override
    public void onPrevious() {
        AllSongFragment ft = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onPrevious();

        MediaPlaybackFragment mPlay = (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
        song = ft.getCerrentSong();
        mPlay.setDataMusic(song);
    }

    @Override
    public void onPlay() {
        AllSongFragment ft = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onPlay();
    }

    @Override
    public void onNext() {
        AllSongFragment ft = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onNext();

        MediaPlaybackFragment mPlay = (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
        song = ft.getCerrentSong();
        mPlay.setDataMusic(song);

    }

    @Override
    public void onDisLike() {
        AllSongFragment ft = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onDisLike();
    }

    @Override
    public void rePeat() {
//        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
//        ft.onR();
    }

    @Override
    public void shuffle() {

    }


    @Override
    public void selectMusic(int position) {
//        txtTitle.setText(song.getTitle());
//        txtAuthor.setText(song.getAuthor());
        this.song = musicService.getMusicManager().getSinpleSong(position);
        this.position = position;
        AllSongFragment mf = (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        mf.setPosition(position);
        mf.isPlayMusic(true);


        for (int i = 0; i < musicService.getMusicManager().getmSongs().size(); i++) {
            musicService.getMusicManager().getmSongs().get(i).setPlay(false);
        }
        musicService.getMusicManager().setCurrentSong(position);
        if (musicService.getMusicManager().isMusicPlaying()){
            musicService.getMusicManager().onResetMusic();
        }
        musicService.getMusicManager().setCurrentSong(position);
        musicService.getMusicManager().onPlay();

        if (!isVertical) {
            MediaPlaybackFragment mPlay = (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
            mPlay.setDataMusic(song);
        }
        // MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
    }

    @Override
    public void show() {

        Fragment fragment;
        if (isVertical) fragment = MediaPlaybackFragment.getINSTANCE(song);

        else fragment = new MediaPlaybackFragment();

        FragmentTransaction fplay = fragmentManager.beginTransaction();
        fplay.add(R.id.musicPlayer, fragment);
        fplay.addToBackStack(null);
        fplay.commit();
    }

    public void onRemoveFragmetMusic() {
        FragmentTransaction fplay = fragmentManager.beginTransaction();
        fplay.replace(R.id.musicPlayer, new MediaPlaybackFragment());
        fplay.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int position = getPositionCorrent();
        outState.putInt(KEY_SONG_POSITION, position);
        outState.putSerializable(KEY_SONG, musicService.getMusicManager().getmSongs().get(position));
        Log.d("bach", "outState");

        if (isConnection) {
            outState.putSerializable(KEY_ALL_SONG, (Serializable) musicService.getMusicManager().getmSongs());
        }
        super.onSaveInstanceState(outState);
    }

    private int getPositionCorrent() {
        if (isConnection) {
            return musicService.getMusicManager().getCurrentSong();
        }
        return -1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }
    }
}