package com.wd.health.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.wd.health.R;
import com.wd.health.adapter.VideoAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.model.Bean;
import com.wd.health.utlis.DataUtils;
import com.wd.health.widget.OnViewPagerListener;
import com.wd.health.widget.PagerLayoutManager;

import java.util.ArrayList;

/**
 * @ProjectName: My Yiliao
 * @Package: com.wd.health.view.fragment
 * @ClassName: ShortVideoFragment
 * @Author: YuYanHe
 * @CreateDate: 2020/2/12 11:29
 */
public class ShortVideoFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<Bean> mDatas = new ArrayList<>();
    private VideoAdapter mAdapter;
    private IjkVideoView mVideoView;
    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.shortvideo_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        PagerLayoutManager mLayoutManager = new PagerLayoutManager(getActivity(), OrientationHelper.VERTICAL);
        mDatas.addAll(DataUtils.getDatas());
        mAdapter = new VideoAdapter(getActivity(), mDatas);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete(View view) {
                playVideo(0, view);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom, View view) {
                playVideo(position, view);
            }

            @Override
            public void onPageRelease(boolean isNext, int position, View view) {
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(view);
            }
        });

    }
    /**
     * 播放视频
     */
    private void playVideo(int position, View view) {
        if (view != null) {
            mVideoView = view.findViewById(R.id.video_view);
            mVideoView.start();
        }
    }

    /**
     * 停止播放
     */
    private void releaseVideo(View view) {
        if (view != null) {
            IjkVideoView videoView = view.findViewById(R.id.video_view);
            videoView.stopPlayback();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        VideoViewManager.instance().releaseVideoPlayer();
    }

}
