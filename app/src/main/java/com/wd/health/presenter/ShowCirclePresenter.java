package com.wd.health.presenter;
/*时间:2020/1/9 0009
创建人:郭学飞*/


import com.wd.health.base.BasePresenter;
import com.wd.health.contract.DataColl;
import com.wd.health.contract.IRequset;

import io.reactivex.Observable;

public class ShowCirclePresenter extends BasePresenter {
    public ShowCirclePresenter(DataColl dataColl) {
        super(dataColl);
    }

    @Override
    protected Observable getModel(IRequset iRequset, Object... arge) {
        return null;
    }
}
