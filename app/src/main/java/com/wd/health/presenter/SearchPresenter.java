package com.wd.health.presenter;

import com.wd.health.base.BasePresenter;
import com.wd.health.contract.DataColl;
import com.wd.health.contract.IRequset;

import io.reactivex.Observable;

/**
 * <p>文件描述：<p>
 * <p>作者：孙尔哲<p>
 * <p>创建时间：2020/1/8<p>
 * <p>更改时间：2020/1/8<p>
 */
public class SearchPresenter extends BasePresenter {

    public SearchPresenter(DataColl dataColl) {
        super(dataColl);
    }

    @Override
    protected Observable getModel(IRequset iRequset, Object... arge) {
        return iRequset.homePageSearch((String) arge[0]);
    }
}
