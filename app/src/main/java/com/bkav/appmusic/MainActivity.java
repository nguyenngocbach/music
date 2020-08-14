package com.bkav.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bkav.appmusic.fragment.AllSongFragment;
import com.bkav.appmusic.fragment.MediaPlaybackFragment;
import com.bkav.appmusic.listener.SongListener;
import com.bkav.appmusic.model.Song;

public class  MainActivity extends AppCompatActivity  implements MediaPlaybackFragment.MediaPlayFragmentListenner {
//    public static int INDEX=-1;
//    public String PREFERENCES="com.bkav.appmusic";
//    public static SharedPreferences sharedPreferences;

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private ImageView imgPlay, imgImage;
    private TextView txtTitle, txtAuthor;
    private LinearLayout layout;


    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sharedPreferences= getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        if (savedInstanceState!=null){
            //INDEX = sharedPreferences.getInt("count", -1);
        }

        init();
    }

    private void init() {
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout= findViewById(R.id.AllSongsFragment);
        imgImage=findViewById(R.id.avatar);
        imgPlay=findViewById(R.id.icon_play_music);
        txtTitle= findViewById(R.id.nameMusic);
        txtAuthor= findViewById(R.id.nameAirsts);

        AllSongFragment songFragment= new AllSongFragment();
        manager= getSupportFragmentManager();
        final FragmentTransaction ft= manager.beginTransaction();
        ft.add(R.id.AllSongsFragment,songFragment);
        ft.commit();

        layout= findViewById(R.id.linearLayout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft= manager.beginTransaction();
                ft.add(R.id.frame_main,new MediaPlaybackFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

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

//    @Override
//    public void selectMusic(Song song) {
//        txtTitle.setText(song.getTitle());
//        txtAuthor.setText(song.getAuthor());
//    }



    @Override
    public void onLike() {

    }

    @Override
    public void onPrevious() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void onNext() {

    }

    @Override
    public void onDisLike() {

    }
}