package com.wd.health.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.DrugsCategoryList;
import com.wd.health.model.DrugsKnowledgeList;
import com.wd.health.presenter.DrugsCategoryPresenter;
import com.wd.health.presenter.DrugsKnowledgePresenter;
import com.wd.health.view.activity.ConditionsfordetailsActivity;
import com.wd.health.view.activity.DetailsofcommonlyuseddrugsActivity;
import com.wd.health.view.adapter.DrugsCategoryAdapter;
import com.wd.health.view.adapter.DrugsKnowledgeAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*时间:2020/1/9
创建人:yang 
创建人:杨靖宇
药品科目分类列表查询
*/
public class DrugFragment extends BaseFragment {
    @BindView(R.id.recy_drugscategory)
    RecyclerView recyDrugscategory;
    @BindView(R.id.recy_diseaseCategory)
    RecyclerView recyDiseaseCategory;
    private DrugsCategoryAdapter drugsCategoryAdapter;
    private DrugsKnowledgeAdapter drugsKnowledgeAdapter;
    private DrugsKnowledgePresenter drugsKnowledgePresenter;

    @Override
    public View getLayoutID(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.drug_layout, null, false);

        return view;
    }

    @Override
    public void initView() {
        DrugsCategoryPresenter drugsCategoryPresenter=new DrugsCategoryPresenter(new drugsColl());
        drugsKnowledgePresenter = new DrugsKnowledgePresenter(new drugsKnowledgeColl());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        recyDiseaseCategory.setLayoutManager(gridLayoutManager);
        recyDrugscategory.setLayoutManager(linearLayoutManager);
        drugsCategoryAdapter = new DrugsCategoryAdapter(getContext());
        drugsKnowledgeAdapter = new DrugsKnowledgeAdapter(getContext());
        recyDiseaseCategory.setAdapter(drugsKnowledgeAdapter);
        recyDrugscategory.setAdapter(drugsCategoryAdapter);
        drugsCategoryPresenter.requsetData();
        drugsKnowledgePresenter.requsetData(1,1,5  );
       // drugsCategoryAdapter.setCollBack();

    }
    //根据药品类目查询常见药品
   class drugsKnowledgeColl implements DataColl<List<DrugsKnowledgeList>>{
        @Override
        public void succeed(List<DrugsKnowledgeList> res) {
           // Log.i("aaa", "succeed: "+res.get(0).name);
            if (res.size()!=0){
                drugsKnowledgeAdapter.categoryclear();
                drugsKnowledgeAdapter.addAll(res);
                drugsKnowledgeAdapter.notifyDataSetChanged();
                drugsKnowledgeAdapter.setOnClickLisnerDrugsKnowledge(new DrugsKnowledgeAdapter.OnClickLisnerDrugsKnowledge() {
                    @Override
                    public void OnClik(int i) {
                        //Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(), DetailsofcommonlyuseddrugsActivity.class);
                        int id = res.get(i).id;
                        intent.putExtra("id",id);
                        intent.putExtra("name",res.get(i).name);
                        startActivity(intent);
                        getActivity().finish();

                    }
                });


            }else {
                 Log.i("aaa", "succeed: "+"無數據");
            }
        }

        @Override
        public void failure(Data data) {

        }
    }
    //药品科目分类列表查询
    class  drugsColl implements DataColl<List<DrugsCategoryList>>{
        @Override
        public void succeed(List<DrugsCategoryList> res) {
            drugsCategoryAdapter.addAll(res);
            drugsCategoryAdapter.notifyDataSetChanged();
            drugsCategoryAdapter.setOnClickLisnerdrugsCategory(new DrugsCategoryAdapter.OnClickLisnerdrugsCategory() {
                @Override
                public void OnClik(int i) {
                    int id = i;
                    //Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                    if (id<=6){
                        Log.e("biao",id+"");
                        drugsKnowledgePresenter.requsetData(id,1,5);
                    }

                }
            });
        }

        @Override
        public void failure(Data data) {

        }
    }
}
