package com.wd.health.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.Department;
import com.wd.health.presenter.DepartmentPresenter;
import com.wd.health.view.adapter.DiseaseAdapter;

import java.util.List;

import butterknife.BindView;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public class PatientFragment extends BaseFragment {
    @BindView(R.id.patient_recycler)
    RecyclerView mPatientRecycler;
    private DiseaseAdapter diseaseAdapter;
    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.patient_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
        DepartmentPresenter departmentPresenter=new DepartmentPresenter(new departmentColl());
        departmentPresenter.requsetData();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mPatientRecycler.setLayoutManager(linearLayoutManager);
        diseaseAdapter = new DiseaseAdapter(getContext());
        mPatientRecycler.setAdapter(diseaseAdapter);
    }

    class departmentColl implements DataColl<List<Department>> {
        @Override
        public void succeed(List<Department> res) {
            //String departmentName = res.get(0).departmentName;
            // Log.i("aaaa", "succeed: "+departmentName);
            diseaseAdapter.addAll(res);
            diseaseAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure(Data data) {

        }
    }
}
