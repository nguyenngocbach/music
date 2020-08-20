package com.bkav.appmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bkav.appmusic.fragment.AllSongFragment;
import com.bkav.appmusic.fragment.MediaPlaybackFragment;
import com.bkav.appmusic.fragment.ToolbarFragment;
import com.bkav.appmusic.listener.SongListener;
import com.bkav.appmusic.model.Song;
import com.bkav.appmusic.service.MusicService;
import com.bkav.appmusic.until.Coast;

public class  MainActivity extends AppCompatActivity  implements  SongListener , MediaPlaybackFragment.MediaPlayFragmentListenner, AllSongFragment.ShowMeDiaPlayListener {
    private static final String KEY_SONG = "com.bkav.appmusic.MainActivity.Song";
    private static final String KEY_SONG_POSITION ="com.bkav.appmusic.MainActivity.Position" ;

    private FragmentManager fragmentManager;
    public boolean isVertical= false;
    public FragmentTransaction fplay;
    private Song song=null;
    private int position=-1;

    private boolean check=false;
    private boolean isConnection=false;
    private MusicService musicService;

    private ServiceConnection mConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
           MusicService.LocalMusic binder= (MusicService.LocalMusic) iBinder;
           isConnection=true;
           musicService= binder.getInstanceService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnection=false;
        }
    };

    public boolean isIsVertical() {
        return isVertical;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!= null){
            check=true;
            song= (Song) savedInstanceState.getSerializable(KEY_SONG);
            position = savedInstanceState.getInt(KEY_SONG_POSITION);
            Log.d("bach","savedInstanceState");
            Log.d("bach",song.getTitle()+"");
            Log.d("bach",song.getAuthor());

        }

        if(findViewById(R.id.vertical_Screen) != null )
            isVertical=true;

        if(findViewById(R.id.vertical_Screen) != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.allSongFragment, new AllSongFragment());
            ft.commit();
        }

        else{
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.allSongFragment, new AllSongFragment());
            ft.commit();

            fplay = fragmentManager.beginTransaction();
            fplay.replace(R.id.musicPlayer, new MediaPlaybackFragment());
            fplay.addToBackStack(null);
            fplay.commit();
        }

        Intent intent= new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AllSongFragment mf= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        if (isConnection){
            Log.d("bachdz","connection");
            mf.addData(musicService.getMusicManager().getmSongs());
        }
        if (check==true){
            mf.setPosition(position);

            if (!isVertical){
                MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
                mPlay.setDataMusic(song);
            }
        }
    }




    @Override
    public void onLike() {
        // todo songthing
//        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
//        ft.onLike();

    }

    @Override
    public void onPrevious() {
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onPrevious();

        MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
        song= ft.getCerrentSong();
        mPlay.setDataMusic(song);
    }

    @Override
    public void onPlay() {
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onPlay();
    }

    @Override
    public void onNext() {
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onNext();

        MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
        song= ft.getCerrentSong();
        mPlay.setDataMusic(song);

    }

    @Override
    public void onDisLike() {
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
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
        this.song=musicService.getMusicManager().getSinpleSong(position);
        this.position= position;
        AllSongFragment mf= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        mf.setPosition(position);

        if (!isVertical){
            MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
            mPlay.setDataMusic(song);
        }
       // MediaPlaybackFragment mPlay= (MediaPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.musicPlayer);
    }

    @Override
    public void show() {

        Fragment fragment;
        if (isVertical) fragment= MediaPlaybackFragment.getINSTANCE(song);

        else fragment=new MediaPlaybackFragment();

        FragmentTransaction fplay = fragmentManager.beginTransaction();
        fplay.add(R.id.musicPlayer,fragment);
        fplay.addToBackStack(null);
        fplay.commit();
    }

    public void onRemoveFragmetMusic(){
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
        if (song!=null){
            outState.putSerializable(KEY_SONG,song);
            outState.putInt(KEY_SONG_POSITION,position);
            Log.d("bach","outState");
        }
        super.onSaveInstanceState(outState);
    }
}