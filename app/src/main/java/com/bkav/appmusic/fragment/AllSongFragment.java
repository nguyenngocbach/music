package com.bkav.appmusic.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bkav.appmusic.MainActivity;
import com.bkav.appmusic.R;
import com.bkav.appmusic.adapter.AllSongAdapter;
import com.bkav.appmusic.listener.SongListener;
import com.bkav.appmusic.model.Song;

import java.util.ArrayList;
import java.util.List;

public class AllSongFragment extends Fragment {
    private SharedPreferences preferences;
    private RecyclerView recyclerView;
    private List<Song> mSongs;
    private AllSongAdapter adapter;

    private ImageView imgPlay, imgImage;
    private TextView txtTitle, txtAuthor;
    private LinearLayout layout;
    private ShowMeDiaPlayListener listener;

    private Song cerrentSong=null;
    public static int index=0;

    MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            listener= (ShowMeDiaPlayListener) context;
            mainActivity= (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.all_song_fragment,container,false);
        recyclerView= view.findViewById(R.id.recycler_song);
        mSongs= new ArrayList<>();
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter= new AllSongAdapter(getContext(),mSongs, (MainActivity) getActivity());
        recyclerView.setAdapter(adapter);

        //
//        for(int i=0;i<mSongs.size();i++){
//            if(mSongs.get(i).isPlay()){
//                cerrentSong=mSongs.get(i);
//            }
//        }

        imgImage=view.findViewById(R.id.avatar);
        imgPlay=view.findViewById(R.id.icon_play_music);
        txtTitle= view.findViewById(R.id.nameMusic);
        txtAuthor= view.findViewById(R.id.nameAirsts);
        layout= view.findViewById(R.id.linearLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.show();
            }
        });

        if (!mainActivity.isVertical){
            layout.setVisibility(View.GONE);
        }
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i=0;i<mSongs.size();i++){
            if(mSongs.get(i).isPlay()){
                cerrentSong=mSongs.get(i);
                index=i;
            }
        }

    }




    public void addData(List<Song> mSongs) {
        if (this.mSongs!=null){
            this.mSongs.clear();
            this.mSongs= new ArrayList<>();
            this.mSongs.addAll(mSongs);
        }
        adapter.notifyDataSetChanged();
    }

    public List<Song> getAllSong(){
        return mSongs;
    }

    public void setPosition(int position){

        for (int i=0;i<mSongs.size();i++){
            mSongs.get(i).setPlay(false);
                }
        mSongs.get(position).setPlay(true);
        txtTitle.setText(mSongs.get(position).getTitle());
        txtAuthor.setText(mSongs.get(position).getAuthor());
        cerrentSong=mSongs.get(position);
        adapter.notifyDataSetChanged();
    }

    public Song getCerrentSong() {
        return cerrentSong;
    }

    // interface
    public interface ShowMeDiaPlayListener{
        void show();
    }

    public void onPrevious(){
        mSongs.get(index).setPlay(false);
        if (index==0){
            index=mSongs.size()-1;
        }
        else index--;
        mSongs.get(index).setPlay(true);
        cerrentSong= mSongs.get(index);
        adapter.notifyDataSetChanged();
    }

    public void onLike(){
        //todo something
    }

    public void onPlay(){
        //todo sonething
    }

    public void onNext(){
        mSongs.get(index).setPlay(false);
        if (index==mSongs.size()){
            index=0;
        }
        else index++;
        mSongs.get(index).setPlay(true);
        cerrentSong= mSongs.get(index);
        adapter.notifyDataSetChanged();
    }

    public void onDisLike(){
        //todo something
    }
}
