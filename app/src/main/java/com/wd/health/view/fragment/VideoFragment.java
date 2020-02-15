package com.wd.health.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.adapter.VideoAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Bean;
import com.wd.health.model.CategoryBean;
import com.wd.health.model.Data;
import com.wd.health.model.VideoBean;
import com.wd.health.presenter.ShortVideoPresenter;
import com.wd.health.utlis.DataUtils;
import com.wd.health.widget.OnViewPagerListener;
import com.wd.health.widget.PagerLayoutManager;

import java.util.ArrayList;
import java.util.List;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/


public class VideoFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<Bean> mDatas = new ArrayList<>();
    private VideoAdapter mAdapter;
    private IjkVideoView mVideoView;

//    private TabLayout tabLayouts;
//    private ViewPager vps;
//    ArrayList<Fragment> list = new ArrayList<>();
//    ArrayList<String> list1 = new ArrayList<>();
//    private ShortVideoPresenter shortVideoPresenter;

    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
       // View view = inflater.inflate(R.layout.video_layout, null, false);
        View view = inflater.inflate(R.layout.shortvideo_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
//        tabLayouts = getActivity().findViewById(R.id.tab_layout);
//        vps = getActivity().findViewById(R.id.vpage);
//        shortVideoPresenter = new ShortVideoPresenter(new shortVideo());
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

//    class shortVideo implements DataColl<List<CategoryBean>> {
//
//       private String names;
//
//        @Override
//        public void succeed(List<CategoryBean> res) {
//            int size = res.size();
//            for (int i = 0; i < size; i++) {
//                names = res.get(i).name;
//                Log.i("aasasas", "succeed: " + names);
//            }
//            list1.add(names);
//            list.add(new ShortVideoFragment());
//            list.add(new ShortVideoFragment());
//            list.add(new ShortVideoFragment());
//            list.add(new ShortVideoFragment());
//            list.add(new ShortVideoFragment());
//            list.add(new ShortVideoFragment());
//            vps.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//                @NonNull
//                @Override
//                public Fragment getItem(int position) {
//                    return list.get(position);
//                }
//
//                @Override
//                public int getCount() {
//                    return list1.size();
//                }
//
//                @Nullable
//                @Override
//                public CharSequence getPageTitle(int position) {
//                    return list1.get(position);
//                }
//            });
//            tabLayouts.setupWithViewPager(vps);
//        }
//
//        @Override
//        public void failure(Data data) {
//            Log.i("TAG", "failure: "+data);
//        }
//    }
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


