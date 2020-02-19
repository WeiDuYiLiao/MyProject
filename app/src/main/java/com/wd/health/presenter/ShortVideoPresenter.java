package com.wd.health.presenter;

import com.wd.health.base.BasePresenter;
import com.wd.health.contract.DataColl;
import com.wd.health.contract.IRequset;

import io.reactivex.Observable;

/**
 * @ProjectName: My Yiliao
 * @Package: com.wd.health.presenter
 * @ClassName: ShortVideoPresenter
 * @Author: YuYanHe
 * @CreateDate: 2020/2/14 14:27
 */
public class ShortVideoPresenter extends BasePresenter {
    public ShortVideoPresenter(DataColl dataColl) {
        super(dataColl);
    }

    @Override
    protected Observable getModel(IRequset iRequset, Object... arge) {
        return iRequset.getCategory();
    }
}
