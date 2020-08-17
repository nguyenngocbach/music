package com.bkav.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
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

public class  MainActivity extends AppCompatActivity  implements  SongListener , MediaPlaybackFragment.MediaPlayFragmentListenner, AllSongFragment.ShowMeDiaPlayListener {
//    public static int INDEX=-1;
//    public String PREFERENCES="com.bkav.appmusic";
//    public static SharedPreferences sharedPreferences;

//    private Toolbar toolbar;
//    private FrameLayout frameLayout;
//    private ImageView imgPlay, imgImage;
//    private TextView txtTitle, txtAuthor;
//    private LinearLayout layout;
    private FragmentManager fragmentManager;
    private boolean isVertical= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sharedPreferences= getSharedPreferences(PREFERENCES,MODE_PRIVATE);
//        if (savedInstanceState!=null){
//            //INDEX = sharedPreferences.getInt("count", -1);
//        }

        if(findViewById(R.id.vertical_Screen) != null )
            isVertical=true;

        if(isVertical) {

            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.allSongFragment, new AllSongFragment());
            ft.commit();

        }

        else{

            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.allSongFragment, new AllSongFragment());
            ft.commit();

            FragmentTransaction fplay = fragmentManager.beginTransaction();
            fplay.add(R.id.musicPlayer, new MediaPlaybackFragment());
            fplay.commit();
        }

        //init();
    }

    private void init() {
       /* toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout= findViewById(R.id.AllSongsFragment);
        imgImage=findViewById(R.id.avatar);
        imgPlay=findViewById(R.id.icon_play_music);
        txtTitle= findViewById(R.id.nameMusic);
        txtAuthor= findViewById(R.id.nameAirsts);
*/
//        AllSongFragment songFragment= new AllSongFragment();
//        manager= getSupportFragmentManager();
//        final FragmentTransaction ft= manager.beginTransaction();
//        ft.add(R.id.AllSongsFragment,songFragment);
//        ft.commit();

//        layout= findViewById(R.id.linearLayout);
//
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft= manager.beginTransaction();
//                ft.add(R.id.frame_main,new MediaPlaybackFragment());
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });

    }

//    public static void saveInDex(int index){
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//        editor.putInt("NUMBERINDEX",index);
//        editor.apply();
//    }
//
//    public static int getINDEX(){
//        INDEX = sharedPreferences.getInt("NUMBERINDEX", -1);
//        return INDEX;
//    }



    @Override
    public void onLike() {
        // todo songthing
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onLike();

    }

    @Override
    public void onPrevious() {
        AllSongFragment ft= (AllSongFragment) getSupportFragmentManager().findFragmentById(R.id.allSongFragment);
        ft.onPrevious();
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
    public void selectMusic(Song song, int position) {
//        txtTitle.setText(song.getTitle());
//        txtAuthor.setText(song.getAuthor());
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
        FragmentTransaction fplay = fragmentManager.beginTransaction();
        fplay.add(R.id.allSongFragment, new MediaPlaybackFragment());
        fplay.addToBackStack(null);
        fplay.commit();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}