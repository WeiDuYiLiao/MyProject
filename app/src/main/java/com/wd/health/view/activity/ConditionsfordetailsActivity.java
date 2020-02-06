package com.wd.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.DiseaseKnowledge;
import com.wd.health.presenter.DiseaseKnowledgePresenter;
import com.wd.health.view.adapter.DrugsKnowledgeAdapter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConditionsfordetailsActivity extends BaseActivity {

    /*    @BindView(R.id.recy_diseaseknowledge)*/
    RecyclerView recyDiseaseknowledge;
    @BindView(R.id.pathology_text)
    TextView pathologyText;
    @BindView(R.id.symptom_text)
    TextView symptomText;
    @BindView(R.id.benefitTaboo_text)
    TextView benefitTabooText;
    @BindView(R.id.westernMedicineTreatment_text)
    TextView westernMedicineTreatmentText;
    @BindView(R.id.chineseMedicineTreatment_text)
    TextView chineseMedicineTreatmentText;
    @BindView(R.id.text_name)
    TextView textName;
    private DrugsKnowledgeAdapter diseaseKnowledgeAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_conditionsfordetails;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");

        Log.i("aaaa", "initView: "+name);
       textName.setText(name);
        DiseaseKnowledgePresenter diseaseKnowledgePresenter = new DiseaseKnowledgePresenter(new diseaseKnowledgeColl());
        diseaseKnowledgePresenter.requsetData(id);


    }


    class diseaseKnowledgeColl implements DataColl<DiseaseKnowledge> {
        @Override
        public void succeed(DiseaseKnowledge res) {
            pathologyText.setText(res.pathology);
            symptomText.setText(res.symptom);
            benefitTabooText.setText(res.benefitTaboo);
            westernMedicineTreatmentText.setText(res.chineseMedicineTreatment);
            chineseMedicineTreatmentText.setText(res.pathology);

        }

        @Override
        public void failure(Data data) {

        }
    }
}
