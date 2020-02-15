package com.wd.health.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.DiseaseSearchVoList;
import com.wd.health.model.DoctorSearchVoList;
import com.wd.health.model.DrugsSearchVoList;
import com.wd.health.model.PopularSearch;
import com.wd.health.model.Search;
import com.wd.health.presenter.PopularSearchPresenter;
import com.wd.health.presenter.SearchPresenter;
import com.wd.health.view.adapter.DiseaseSearchVoListAdapter;
import com.wd.health.view.adapter.DoctorSearchVoListAdapter;
import com.wd.health.view.adapter.DrugsSearchVoListAdapter;
import com.wd.health.view.adapter.HotsearchAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SousuoActivity extends BaseActivity {


    @BindView(R.id.recy_hotsearch)
    RecyclerView recyHotsearch;
    @BindView(R.id.hotsousuo_ed)
    EditText hotsousuoEd;
    @BindView(R.id.recy_doctor)
    RecyclerView recyDoctor;
    @BindView(R.id.recy_drugssearchvo)
    RecyclerView recyDrugssearchvo;
    @BindView(R.id.recy_diseaseearchsvo)
    RecyclerView recyDiseaseearchsvo;
    private HotsearchAdapter hotsearchAdapter;
    private DoctorSearchVoListAdapter doctorSearchVoListAdapter;
    private DrugsSearchVoListAdapter drugsSearchVoListAdapter;
    private DiseaseSearchVoListAdapter diseaseSearchVoListAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_sousuo;
    }

    @Override
    protected void initView() {

        //热门搜索

        PopularSearchPresenter popularSearchPresenter = new PopularSearchPresenter(new popularSearchColl());
        popularSearchPresenter.requsetData();
        //首页搜索
        SearchPresenter searchPresenter = new SearchPresenter(new SearchColl());
        searchPresenter.requsetData("生");

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, 1);
        LinearLayoutManager doctorManager = new LinearLayoutManager(this);
        LinearLayoutManager drugssearchvoManager = new LinearLayoutManager(this);
        LinearLayoutManager diseaseManager = new LinearLayoutManager(this);
        recyDiseaseearchsvo.setLayoutManager(diseaseManager);
        recyDoctor.setLayoutManager(doctorManager);
        recyDrugssearchvo.setLayoutManager(drugssearchvoManager);
        recyHotsearch.setLayoutManager(manager);

        hotsearchAdapter = new HotsearchAdapter(this);
        doctorSearchVoListAdapter = new DoctorSearchVoListAdapter(this);
        drugsSearchVoListAdapter = new DrugsSearchVoListAdapter(this);
        diseaseSearchVoListAdapter = new DiseaseSearchVoListAdapter(this);

        recyDiseaseearchsvo.setAdapter(diseaseSearchVoListAdapter);
        recyDrugssearchvo.setAdapter(drugsSearchVoListAdapter);
        recyDoctor.setAdapter(doctorSearchVoListAdapter);
        recyHotsearch.setAdapter(hotsearchAdapter);

    }

    //首页搜索
    class SearchColl implements DataColl<Search> {

        @Override
        public void succeed(Search res) {
            //搜索结果-病症
            List<DiseaseSearchVoList> diseaseSearchVoList = res.diseaseSearchVoList;
            //搜索结果-医生
            List<DoctorSearchVoList> doctorSearchVoList = res.doctorSearchVoList;
            //搜索结果-药品
            List<DrugsSearchVoList> drugsSearchVoList = res.drugsSearchVoList;

            drugsSearchVoListAdapter.addAll(drugsSearchVoList);
            doctorSearchVoListAdapter.addAll(doctorSearchVoList);
            diseaseSearchVoListAdapter.addAll(diseaseSearchVoList);

            drugsSearchVoListAdapter.notifyDataSetChanged();
            doctorSearchVoListAdapter.notifyDataSetChanged();
            diseaseSearchVoListAdapter.notifyDataSetChanged();

        }

        @Override
        public void failure(Data data) {

        }
    }

    //热门搜索
    class popularSearchColl implements DataColl<List<PopularSearch>> {
        @Override
        public void succeed(List<PopularSearch> res) {
            int size = res.size();
            for (int i = 0; i <size ; i++) {
                Log.i("aaa", "succeed: "+res.get(i).name);

            }
            hotsearchAdapter.addAll(res);
            hotsearchAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure(Data data) {

        }
    }

}
