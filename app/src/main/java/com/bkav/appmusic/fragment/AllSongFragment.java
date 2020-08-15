package com.bkav.appmusic.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.all_song_fragment,container,false);
        recyclerView= view.findViewById(R.id.recycler_song);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        addData();
        adapter= new AllSongAdapter(getContext(),mSongs, (MainActivity) getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void addData() {
        mSongs=  new ArrayList<>();
        mSongs.add(new Song("Hello","abcdef",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,true));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));
        mSongs.add(new Song("Nời này có anh","Sơn tùng - MTP",1111,false));

    }

    public List<Song> getAllSong(){
        return mSongs;
    }

    public void setPosition(int position){

        for (int i=0;i<mSongs.size();i++){
            mSongs.get(i).setPlay(false);
                }
        mSongs.get(position).setPlay(true);

        adapter.notifyDataSetChanged();
    }
}
