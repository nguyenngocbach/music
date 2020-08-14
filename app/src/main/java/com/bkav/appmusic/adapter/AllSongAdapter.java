package com.bkav.appmusic.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkav.appmusic.MainActivity;
import com.bkav.appmusic.R;
import com.bkav.appmusic.listener.SongListener;
import com.bkav.appmusic.model.Song;

import java.util.List;

public class AllSongAdapter extends RecyclerView.Adapter<AllSongAdapter.ViewHolder> {
    private Context mContext;
    private List<Song> mSongs;
    private SongListener listener;

    public AllSongAdapter(Context mContext, List<Song> mSongs, SongListener listener) {
        this.mContext = mContext;
        this.mSongs = mSongs;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mSongs.get(position).isPlay()==true) return 1;
        return 0;
    }

    @NonNull
    @Override
    public AllSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        if (viewType==1){
             view= LayoutInflater.from(mContext).inflate(R.layout.song_line_play,parent,false);
        }
        else {
             view= LayoutInflater.from(mContext).inflate(R.layout.song_line,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllSongAdapter.ViewHolder holder, final int position) {
        int index=position+1;
        holder.txtStt.setText(""+index);
        holder.onBind(mSongs.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int INDEXX= MainActivity.getINDEX();
//                if (INDEXX==position) return;
//                mSongs.get(position).setPlay(true);
//                if (INDEXX!=-1)
//                    mSongs.get(INDEXX).setPlay(false);
//                INDEXX= position;
                listener.selectMusic(mSongs.get(position));
                //MainActivity.saveInDex(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStt,txtTitle, txtAuthor;
        ImageView iconPlayMusic, iconMore;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStt= itemView.findViewById(R.id.txt_stt);
            txtTitle= itemView.findViewById(R.id.nameMusic);
            txtAuthor= itemView.findViewById(R.id.nameAirsts);
            iconPlayMusic= itemView.findViewById(R.id.icon_play_music);
            iconMore= itemView.findViewById(R.id.icon_more);
            layout= itemView.findViewById(R.id.click_item);
        }

        public void onBind(Song song) {
            txtTitle.setText(song.getTitle());
            txtAuthor.setText(song.getAuthor());
            if (song.isPlay()){
                txtStt.setVisibility(View.INVISIBLE);
                iconPlayMusic.setVisibility(View.VISIBLE);
            }
        }
    }

}
