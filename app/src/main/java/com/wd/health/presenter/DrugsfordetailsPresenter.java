package com.wd.health.presenter;

import com.wd.health.base.BasePresenter;
import com.wd.health.contract.DataColl;
import com.wd.health.contract.IRequset;

import io.reactivex.Observable;

/*时间:2020/2/3
创建人:yang 
创建人:杨靖宇*/
public class DrugsfordetailsPresenter extends BasePresenter {
    public DrugsfordetailsPresenter(DataColl dataColl) {
        super(dataColl);
    }

    @Override
    protected Observable getModel(IRequset iRequset, Object... arge) {
        return iRequset.findDrugsKnowledge((int)arge[0]);
    }
}
