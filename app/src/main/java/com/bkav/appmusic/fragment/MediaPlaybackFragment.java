package com.bkav.appmusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bkav.appmusic.MainActivity;
import com.bkav.appmusic.R;
import com.bkav.appmusic.model.Song;
import com.bkav.appmusic.until.Coast;

public class MediaPlaybackFragment extends Fragment implements View.OnClickListener{
    public static final String KEY_FRAGGMENT= "com.bkav.appmusic.fragment.MediaPlaybackFragment";

    private ImageView imgMusic,imgAvatar;
    private ImageView iconLike, iconPrevious,iconPlay, iconNext,iconDislike,iconMore,iconListMusic, iconRePeat, iconShuffle;
    private TextView txtTime, txtTotalTime,txtTitel,txtAuthor;
    private SeekBar seekBar;

    private MediaPlayFragmentListenner listenner;

    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            listenner= (MediaPlayFragmentListenner) context;
            mainActivity= (MainActivity) context;
        }
        else {
            throw new ClassCastException("onAttach Methods have problem !");
        }
    }

    public MediaPlaybackFragment() {
    }

    public static MediaPlaybackFragment getINSTANCE(Song song){
        MediaPlaybackFragment fragment= new MediaPlaybackFragment();
        Bundle bundle= new Bundle();
        //bundle.putSerializable(KEY_FRAGGMENT,song);
        fragment.setArguments(Coast.getSongFormat(song));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.media_play_back_fragment,container,false);
        imgMusic= view.findViewById(R.id.img_music);
        imgAvatar= view.findViewById(R.id.icon_avata);
        iconMore= view.findViewById(R.id.icon_more);
        iconListMusic= view.findViewById(R.id.icon_queue);
        iconLike= view.findViewById(R.id.iconLike);
        iconPrevious= view.findViewById(R.id.iconPrevious);
        iconPlay= view.findViewById(R.id.iconPlay);
        iconNext= view.findViewById(R.id.iconNext);
        iconDislike= view.findViewById(R.id.iconDislike);
        iconRePeat= view.findViewById(R.id.icon_repeat);
        iconShuffle= view.findViewById(R.id.icon_shuffle);
        txtAuthor= view.findViewById(R.id.txtAuthor);
        txtTitel= view.findViewById(R.id.txtTitle);
        txtTime= view.findViewById(R.id.txt_startTime);
        txtTotalTime= view.findViewById(R.id.txt_totalTime);
        seekBar= view.findViewById(R.id.seebar_ok);
        listenner();

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        if (mainActivity.isVertical && getArguments()!=null){

            setTitle(getArguments());
        }

        return view;
    }


    private void listenner() {
        iconMore.setOnClickListener(this);
        iconListMusic.setOnClickListener(this);
        iconLike.setOnClickListener(this);
        iconPrevious.setOnClickListener(this);
        iconPlay.setOnClickListener(this);
        iconNext.setOnClickListener(this);
        iconDislike.setOnClickListener(this);
        iconRePeat.setOnClickListener(this);
        iconShuffle.setOnClickListener(this);
    }

    public void setTitle(Bundle bundle){
        String title= bundle.getString(Coast.TITLE_SONG,"1");
        String author= bundle.getString(Coast.AUTHOR_SONG,"1");
        txtAuthor.setText(author);
        txtTitel.setText(title);
    }


    public void setDataMusic(Song s){
        txtAuthor.setText(s.getAuthor());
        txtTitel.setText(s.getTitle());
        //imgMusic.setBackgroundResource();
        //imgAvatar.setBackgroundResource();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_more:
                break;
            case R.id.icon_queue:
                break;
            case R.id.iconLike:
                listenner.onLike();
                break;
            case R.id.iconPrevious:
                listenner.onPrevious();
                break;
            case R.id.iconPlay:
                listenner.onPlay();
                break;
            case R.id.iconNext:
                listenner.onNext();
                break;
            case R.id.iconDislike:
                listenner.onDisLike();
                break;
            case R.id.icon_repeat:
                listenner.rePeat();
                break;
            case R.id.icon_shuffle:
                listenner.shuffle();
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onDestroy() {
//        if (!mainActivity.isVertical) {
//            MediaPlaybackFragment mediaPlaybackFragment= (MediaPlaybackFragment) getChildFragmentManager().findFragmentById(R.id.musicPlayer);
//           if (mediaPlaybackFragment.isRemoving()) mainActivity.onRemoveFragmetMusic();
//        }

        super.onDestroy();
    }

    public interface MediaPlayFragmentListenner{
        void onLike();

        void onPrevious();

        void onPlay();

        void onNext();

        void onDisLike();

        void rePeat();

        void shuffle();
    }
}
