package com.wd.health.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.wd.health.R;
import com.wd.health.model.Bean;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Bean> mDatas;
    private final PlayerConfig playerConfig;

    public VideoAdapter(Context context, ArrayList<Bean> datas) {
        mContext = context;
        mDatas = datas;

        playerConfig = new PlayerConfig.Builder()
                .enableCache()
                .usingSurfaceView()
                .savingProgress()
                .disableAudioFocus()
                .setLooping()
                .addToPlayerManager()
                .build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.videoView.setUrl(mDatas.get(position).url);
        holder.videoView.setPlayerConfig(playerConfig);
        holder.videoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public IjkVideoView videoView;

        public ViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
        }
    }
}
