package com.bkav.appmusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bkav.appmusic.R;

public class ToolbarFragment extends Fragment {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private ImageView imgPlay, imgImage;
    private TextView txtTitle, txtAuthor;
    private LinearLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_fragment, container,false);
        toolbar= view.findViewById(R.id.toolbar);
        //getContext().ge(toolbar);
        frameLayout= view.findViewById(R.id.AllSongsFragment);
        imgImage=view.findViewById(R.id.avatar);
        imgPlay=view.findViewById(R.id.icon_play_music);
        txtTitle= view.findViewById(R.id.nameMusic);
        txtAuthor= view.findViewById(R.id.nameAirsts);
        return view;
    }
}
