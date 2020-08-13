package com.bkav.appmusic.fragment;

import android.content.Context;
import android.os.Bundle;
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

public class MediaPlaybackFragment extends Fragment {
    public static final String KEY_FRAGGMENT= "com.bkav.appmusic.fragment.MediaPlaybackFragment";

    private ImageView imgMusic,imgAvatar;
    private ImageView iconLike, iconPrevious,iconPlay, iconNext,iconDislike,iconMore,iconListMusic;
    private TextView txtTime, txtTotalTime,txtTitel,txtAuthor;
    private SeekBar seekBar;

    private MediaPlayFragmentListenner listenner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            listenner= (MediaPlayFragmentListenner) context;
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
        bundle.putSerializable(KEY_FRAGGMENT,song);
        fragment.setArguments(bundle);
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
        txtAuthor= view.findViewById(R.id.txtAuthor);
        txtTitel= view.findViewById(R.id.txtTitle);
        txtTime= view.findViewById(R.id.txt_startTime);
        txtTotalTime= view.findViewById(R.id.txt_totalTime);
        seekBar= view.findViewById(R.id.seebar_ok);
        return view;
    }

    public interface MediaPlayFragmentListenner{
        void onLike();
        void onPrevious();
        void onPlay();
        void onNext();
        void onDisLike();
    }
}
