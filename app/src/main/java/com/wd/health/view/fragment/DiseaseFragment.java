package com.wd.health.view.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.Department;
import com.wd.health.model.DiseaseCategory;
import com.wd.health.presenter.DepartmentPresenter;
import com.wd.health.presenter.DiseaseCategoryPresenter;
import com.wd.health.view.activity.ConditionsfordetailsActivity;
import com.wd.health.view.adapter.DiseaseAdapter;
import com.wd.health.view.adapter.DiseaseCategoryAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇*/
public class DiseaseFragment extends BaseFragment {
    @BindView(R.id.recy_department)
    RecyclerView recyDepartment;
    @BindView(R.id.recy_diseaseCategory)
    RecyclerView recyDiseaseCategory;
    private DiseaseAdapter diseaseAdapter;
    private DiseaseCategoryPresenter diseaseCategoryPresenter;
    private DiseaseCategoryAdapter diseaseCategoryAdapter;


    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.disease_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
        //查询科室列表
        DepartmentPresenter departmentPresenter = new DepartmentPresenter(new departmentColl());
        //根据科室查询对应病症
        diseaseCategoryPresenter = new DiseaseCategoryPresenter(new CategoryColl());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyDepartment.setLayoutManager(linearLayoutManager);
        recyDiseaseCategory.setLayoutManager(gridLayoutManager);
        diseaseAdapter = new DiseaseAdapter(getContext());
        diseaseCategoryAdapter = new DiseaseCategoryAdapter(getContext());

        recyDepartment.setAdapter(diseaseAdapter);
        recyDiseaseCategory.setAdapter(diseaseCategoryAdapter);
        departmentPresenter.requsetData();
        diseaseCategoryPresenter.requsetData(7);



    }
    //根据科室查询对应病症
    class CategoryColl implements DataColl<List<DiseaseCategory>> {
        @Override
        public void succeed(List<DiseaseCategory> res) {
            diseaseCategoryAdapter.categoryclear();
            diseaseCategoryAdapter.addAll(res);
            diseaseCategoryAdapter.notifyDataSetChanged();
            diseaseCategoryAdapter.setOnClickLisnerDiseaseCategory(new DiseaseCategoryAdapter.OnClickLisnerDiseaseCategory() {
                @Override
                public void OnClik(int i) {
                    Intent intent=new Intent(getContext(), ConditionsfordetailsActivity.class);
                    int id = res.get(i).id;
                    intent.putExtra("id",id);
                    intent.putExtra("name",res.get(i).name);
                    startActivity(intent);
                   getActivity().finish();

                }
            });

        }

        @Override
        public void failure(Data data) {

        }
    }
    //根据科室查询对应病症

    //查询科室列表
    class departmentColl implements DataColl<List<Department>> {
        @Override
        public void succeed(List<Department> res) {

            diseaseAdapter.addAll(res);
            diseaseAdapter.notifyDataSetChanged();
            diseaseAdapter.setOnClickLisnerdisease(new DiseaseAdapter.OnClickLisnerdisease() {
                @Override
                public void OnClik( int i) {
                    int id =i;
                    diseaseCategoryPresenter.requsetData(id);
                }
            });

        }

        @Override
        public void failure(Data data) {

        }
    }

}
