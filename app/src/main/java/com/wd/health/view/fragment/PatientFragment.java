package com.wd.health.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.Department;
import com.wd.health.model.ShowCircleBean;
import com.wd.health.presenter.DepartmentPresenter;
import com.wd.health.presenter.ShowCirclePresenter;
import com.wd.health.view.adapter.CircleAdapter;
import com.wd.health.view.adapter.DiseaseAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/*时间:2020/1/8
创建人:yang
创建人:杨靖宇*/
public class PatientFragment extends BaseFragment {
    @BindView(R.id.patient_recycler)
    RecyclerView mPatientRecycler;
    @BindView(R.id.circle_recycler)
    RecyclerView mCircleRecycler;
    @BindView(R.id.text_department)
    TextView mTextDepartment;
    private DiseaseAdapter diseaseAdapter;
    private CircleAdapter circleAdapter;

    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.patient_layout, null, false);
        return view;
    }

    @Override
    public void initView() {
        //请求科室接口
        DepartmentPresenter departmentPresenter = new DepartmentPresenter(new departmentColl());
        departmentPresenter.requsetData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mPatientRecycler.setLayoutManager(linearLayoutManager);
        diseaseAdapter = new DiseaseAdapter(getContext());
        mPatientRecycler.setAdapter(diseaseAdapter);


        //请求各科室的病友圈信息
        ShowCirclePresenter showCirclePresenter = new ShowCirclePresenter(new ShowCircle());
        HashMap<String, String> circlemap = new HashMap<>();
        circlemap.put("departmentId","7");
        circlemap.put("page","2");
        circlemap.put("count","5");
        showCirclePresenter.requsetData(circlemap);
        LinearLayoutManager linear = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mCircleRecycler.setLayoutManager(linear);
        circleAdapter = new CircleAdapter(getContext());
        mCircleRecycler.setAdapter(circleAdapter);
        diseaseAdapter.setClickLisnerdisease(new DiseaseAdapter.OnClickLisnerdisease() {

            @Override
            public void OnClikcheck(String str) {
                mTextDepartment.setText(str);
            }

            @Override
            public void OnClik(View view, int i) {

            }

            @Override
            public void Circle(int departmentid) {
                HashMap<String, String> circlemap = new HashMap<>();
                circlemap.put("departmentId",departmentid+"");
                circlemap.put("page","2");
                circlemap.put("count","5");
                showCirclePresenter.requsetData(circlemap);
            }
        });
    }


    //科室接口返回结果
    class departmentColl implements DataColl<List<Department>> {
        @Override
        public void succeed(List<Department> res) {
            //String departmentName = res.get(0).departmentName;
            // Log.i("aaaa", "succeed: "+departmentName);

            diseaseAdapter.addAll(res, true);
            diseaseAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure(Data data) {

        }
    }

    //病友圈返回数据结果
    private class ShowCircle implements DataColl<List<ShowCircleBean>> {

        @Override
        public void succeed(List<ShowCircleBean> res) {
            //先对集合数据进行清空
            circleAdapter.cancelAll();
            //去添加集合数据
            circleAdapter.addAll(res);
            //刷新数据已至于不会叠加
            circleAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure(Data data) {

        }
    }
}
