package com.wd.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.contract.DataColl;
import com.wd.health.model.Data;
import com.wd.health.model.DrugsKnowledge;
import com.wd.health.presenter.DrugsfordetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsofcommonlyuseddrugsActivity extends BaseActivity {

    @BindView(R.id.pharmaceuticalingredients)
    TextView pharmaceuticalingredients;
    @BindView(R.id.contraindicationindrugusing)
    TextView contraindicationindrugusing;
    @BindView(R.id.majorfunction)
    TextView majorfunction;
    @BindView(R.id.usageanddosage)
    TextView usageanddosage;
    @BindView(R.id.drugproperties)
    TextView drugproperties;
    @BindView(R.id.packingspecification)
    TextView packingspecification;
    @BindView(R.id.adversereaction)
    TextView adversereaction;
    @BindView(R.id.holdingconditions)
    TextView holdingconditions;
    @BindView(R.id.mattersneedattention)
    TextView mattersneedattention;
    @BindView(R.id.approvalnumber)
    TextView approvalnumber;
    @BindView(R.id.text_name)
    TextView textName;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_detailsofcommonlyuseddrugs;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        DrugsfordetailsPresenter drugsfordetailsPresenter = new DrugsfordetailsPresenter(new DrugsfordetailsColl());
        drugsfordetailsPresenter.requsetData(id);

    }



    class DrugsfordetailsColl implements DataColl<DrugsKnowledge> {

        @Override
        public void succeed(DrugsKnowledge res) {
            textName.setText(res.name);
            pharmaceuticalingredients.setText(res.component);
            contraindicationindrugusing.setText(res.taboo);
            majorfunction.setText(res.effect);
            usageanddosage.setText(res.usage);
            drugproperties.setText(res.shape);
            packingspecification.setText("6g*10袋");
            adversereaction.setText(res.sideEffects);
            holdingconditions.setText(res.storage);
            mattersneedattention.setText(res.mindMatter);
            approvalnumber.setText(res.approvalNumber);
        }

        @Override
        public void failure(Data data) {

        }
    }
}
