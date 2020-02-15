package com.wd.health.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.contract.InquiryBean;
import com.wd.health.contract.XiangqingBase;
import com.wd.health.contract.ZixunBean;
import com.wd.health.model.Banner;
import com.wd.health.model.Data;
import com.wd.health.presenter.BannderPresenter;
import com.wd.health.presenter.InquiryPresenter;
import com.wd.health.presenter.XiangqingPresenter;
import com.wd.health.presenter.ZixunPresenter;
import com.wd.health.view.Adpter.InquiryAdpter;
import com.wd.health.view.activity.KnowledgeActivity;
import com.wd.health.view.activity.MyActivity;
import com.wd.health.view.activity.SousuoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>文件描述：<p>
 * <p>作者：孙尔哲<p>
 * <p>创建时间：2020/1/11<p>
 * <p>更改时间：2020/1/11<p>
 */
public class FirstFragment extends BaseFragment {


    @BindView(R.id.lun_imageUrl)
    SimpleDraweeView lunImageUrl;
    @BindView(R.id.show_banner)
    XBanner showBanner;
    @BindView(R.id.show_disease)
    ImageView showDisease;
    @BindView(R.id.changyoubingqing)
    TextView changyoubingqing;
    @BindView(R.id.show_drugs)
    ImageView showDrugs;
    @BindView(R.id.rv_cinema)
    RecyclerView rvCinema;
    @BindView(R.id.text_login)
    ImageView textLogin;
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.show_image)
    ImageView showImage;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.recy_view1)
    RecyclerView recyView1;
    private XBanner show_banner;
    private View view;
    private SimpleDraweeView lun_imageUrl;
    private InquiryPresenter inquiryPresenter;
    private ZixunPresenter zixunPresenter;
    private GridLayoutManager gridLayoutManager;
    private ZixunAdpter zixunAdpter;
    private XiangqingPresenter xiangqingPresenter;
    private XiangqingAdpter xiangqingAdpter;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private SharedPreferences.Editor name;
    private SharedPreferences.Editor id;

    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.first_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
        show_banner = view.findViewById(R.id.show_banner);

        //轮播
        BannderPresenter bannderPresenter = new BannderPresenter(new bannderColl());
        bannderPresenter.requsetData();

        //问诊咨询
        inquiryPresenter = new InquiryPresenter(new InquiryPre());
        inquiryPresenter.requsetData();


        //健康咨询
        zixunPresenter = new ZixunPresenter(new ZixunPre());
        zixunPresenter.requsetData();

        //健康咨询
        gridLayoutManager = new GridLayoutManager(getContext(), 5);
        zixunAdpter = new ZixunAdpter(getContext());
        recyView.setLayoutManager(gridLayoutManager);
        recyView.setAdapter(zixunAdpter);
        zixunAdpter.setOnActu(new ZixunAdpter.OnActu() {
            @Override
            public void onActi(int Id, String name) {
                xiangqingPresenter.requsetData(Id, 1, 5);
                sp = getActivity().getSharedPreferences("xiang", Context.MODE_PRIVATE);
                edit = sp.edit();
                id = edit.putInt("Id", Id);
                FirstFragment.this.name = edit.putString("Name", name);
                edit.commit();
            }
        });

        //健康详情
        xiangqingPresenter = new XiangqingPresenter(new XiangqingPre());
        xiangqingPresenter.requsetData(1, 1, 5);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyView1.setLayoutManager(linearLayoutManager);

        xiangqingAdpter = new XiangqingAdpter(getContext());
        recyView1.setAdapter(xiangqingAdpter);



    }
    @OnClick({R.id.show_disease, R.id.show_drugs,R.id.sousuo,R.id.text_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_disease:
                Intent intent = new Intent(getContext(), KnowledgeActivity.class);
                startActivity(intent);

                break;
            case R.id.show_drugs:
                Intent intent1 = new Intent(getContext(), KnowledgeActivity.class);
                intent1.putExtra("yaopin",6);
                startActivity(intent1);
                break;
            case R.id.sousuo:
                Intent intentsousuo = new Intent(getContext(), SousuoActivity.class);
                startActivity(intentsousuo);
                //getActivity().finish();
                break;
            case R.id.text_login:
                Intent myintent = new Intent(getContext(), MyActivity.class);
                startActivity(myintent);
                break;
        }
    }

    //轮播图
    private class bannderColl implements DataColl<List<Banner>> {
        @Override
        public void succeed(List<Banner> res) {
            show_banner.setData(res, null);
            show_banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Log.d("sssst", res.get(position).imageUrl);
                    Glide.with(getActivity()).load(res.get(position).imageUrl).into((ImageView) view);
                    //lun_imageUrl.setImageURI(res.get(position).imageUrl);
                }
            });
            show_banner.setPageTransformer(Transformer.Cube);
            show_banner.setAutoPalyTime(2000);
            show_banner.setAutoPlayAble(true);
        }

        @Override
        public void failure(Data data) {

        }
    }

    //问诊咨询
    private class InquiryPre implements DataColl<List<InquiryBean>> {
        @Override
        public void succeed(List<InquiryBean> res) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
            rvCinema.setLayoutManager(gridLayoutManager);
            InquiryAdpter inquiryAdpter = new InquiryAdpter(R.layout.show_inquiry_name, res);
            rvCinema.setAdapter(inquiryAdpter);
        }

        @Override
        public void failure(Data data) {

        }
    }

    //健康咨询
    private class ZixunPre implements DataColl<List<ZixunBean>> {
        @Override
        public void succeed(List<ZixunBean> res) {
            zixunAdpter.addAll(res);
            zixunAdpter.notifyDataSetChanged();
        }

        @Override
        public void failure(Data data) {

        }
    }

    //健康详情
    private class XiangqingPre implements DataColl<List<XiangqingBase>> {
        @Override
        public void succeed(List<XiangqingBase> res) {
            xiangqingAdpter.clear();
            xiangqingAdpter.addAll(res);
            xiangqingAdpter.notifyDataSetChanged();

            xiangqingAdpter.setJing(new XiangqingAdpter.Jing() {
                @Override
                public void gg(int id) {
                    for (int i = 0; i < res.size(); i++) {
                        res.get(id).getId();
                    }
                }
            });
        }


        @Override
        public void failure(Data data) {

        }
    }
}

