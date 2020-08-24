package com.bkav.appmusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.bkav.appmusic.service.MusicManager;
import com.bkav.appmusic.until.Coast;

public class MediaPlaybackFragment extends Fragment implements View.OnClickListener {
    public static final String KEY_FRAGGMENT = "com.bkav.appmusic.fragment.MediaPlaybackFragment";

    private ImageView imgMusic, imgAvatar;
    private ImageView iconLike, iconPrevious, iconPlay, iconNext, iconDislike, iconMore, iconListMusic, iconRePeat, iconShuffle;
    private TextView txtTime, txtTotalTime, txtTitel, txtAuthor;
    private SeekBar seekBar;

    private MediaPlayFragmentListenner listenner;

    private MainActivity mainActivity;
    private MusicManager musicManager;

    private Song songPlay;
    private boolean isPlay;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            UpdateUI();
            handler.postDelayed(this, 300);
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            listenner = (MediaPlayFragmentListenner) context;
            mainActivity = (MainActivity) context;
        } else {
            throw new ClassCastException("onAttach Methods have problem !");
        }
    }

    public MediaPlaybackFragment() {
    }

    public static MediaPlaybackFragment getINSTANCE(Song song) {
        MediaPlaybackFragment fragment = new MediaPlaybackFragment();
        Bundle bundle = new Bundle();
        //bundle.putSerializable(KEY_FRAGGMENT,song);
        fragment.setArguments(Coast.getSongFormat(song));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_play_back_fragment, container, false);
        Log.d("bachdz","Fragment Music : onCreateView");
        imgMusic = view.findViewById(R.id.img_music);
        imgAvatar = view.findViewById(R.id.icon_avata);
        iconMore = view.findViewById(R.id.icon_more);
        iconListMusic = view.findViewById(R.id.icon_queue);
        iconLike = view.findViewById(R.id.iconLike);
        iconPrevious = view.findViewById(R.id.iconPrevious);
        iconPlay = view.findViewById(R.id.iconPlay);
        iconNext = view.findViewById(R.id.iconNext);
        iconDislike = view.findViewById(R.id.iconDislike);
        iconRePeat = view.findViewById(R.id.icon_repeat);
        iconShuffle = view.findViewById(R.id.icon_shuffle);
        txtAuthor = view.findViewById(R.id.txtAuthor);
        txtTitel = view.findViewById(R.id.txtTitle);
        txtTime = view.findViewById(R.id.txt_startTime);
        txtTotalTime = view.findViewById(R.id.txt_totalTime);
        seekBar = view.findViewById(R.id.seebar_ok);
        listenner();
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        if (mainActivity.isVertical && getArguments() != null) {

            setTitle(getArguments());
        }

        iconPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) {
                    iconPlay.setImageResource(R.drawable.costom_play);
                    isPlay = !isPlay;
                } else {
                    iconPlay.setImageResource(R.drawable.custom_play_pause);
                    isPlay = !isPlay;
                }

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    if (musicManager != null) {
                        musicManager.setSeek(i);
                        seekBar.setProgress(i);
                        txtTime.setText(getDuration(i + ""));
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        handler.postDelayed(runnable, 300);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("bachdz","Fragment Music : onActivityCreated");
        if (mainActivity.getMusicService()==null)
            Log.d("bachdz","Fragment Music : onActivityCreated");
        if (mainActivity.getMusicService().getMusicManager()==null)
            Log.d("bachdz","Fragment Music : onActivityCreated");
        musicManager = mainActivity.getMusicService().getMusicManager();
        seekBar.setMax(Integer.parseInt(musicManager.getSongIsPlay().getDuration()));
        songPlay = musicManager.getSongIsPlay();
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

    public void isPlayMusic(boolean s) {
        if (s) iconPlay.setImageResource(R.drawable.costom_play);
        else iconPlay.setImageResource(R.drawable.custom_play_pause);
    }

    public void setTitle(Bundle bundle) {
        String title = bundle.getString(Coast.TITLE_SONG, "1");
        String author = bundle.getString(Coast.AUTHOR_SONG, "1");
        isPlay = bundle.getBoolean(Coast.IS_PLAY, false);
        txtAuthor.setText(author);
        txtTitel.setText(title);
        if (isPlay) {
            iconPlay.setImageResource(R.drawable.costom_play);
        } else iconPlay.setImageResource(R.drawable.custom_play_pause);
    }


    public void setDataMusic(Song s) {
        txtAuthor.setText(s.getAuthor());
        txtTitel.setText(s.getTitle());
        //imgMusic.setBackgroundResource();
        //imgAvatar.setBackgroundResource();
    }

    private String getDuration(String time) {
        long duration = Long.parseLong(time);
        int minutes = (int) (duration / 1000 / 60);
        int seconds = (int) ((duration / 1000) % 60);
        return minutes + " : " + seconds;
    }

    public void UpdateUI() {
        if (musicManager != null) {
            String totalTime = getDuration(musicManager.getSongIsPlay().getDuration());
            txtTotalTime.setText(totalTime + "");
            String realTime = getDuration(musicManager.getTimeCurrents() + "");
            txtTime.setText(realTime);
            //seekBar.setMax(Integer.parseInt(musicManager.getSongIsPlay().getDuration()));
            seekBar.setProgress(musicManager.getTimeCurrents());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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


    public void setMusicManager(MusicManager musicManager) {
        this.musicManager = musicManager;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    public interface MediaPlayFragmentListenner {
        void onLike();

        void onPrevious();

        void onPlay();

        void onNext();

        void onDisLike();

        void rePeat();

        void shuffle();
    }
}
