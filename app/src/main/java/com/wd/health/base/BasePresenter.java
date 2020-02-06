package com.wd.health.base;


import com.wd.health.contract.DataColl;
import com.wd.health.contract.IRequset;
import com.wd.health.model.Data;
import com.wd.health.utlis.HttpUtlis;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public abstract class BasePresenter {
    private DataColl dataColl;

    public BasePresenter(DataColl dataColl) {
        this.dataColl = dataColl;
    }
    public void requsetData(Object...arge){

        IRequset iRequset = HttpUtlis.getInstance().create(IRequset.class);
        getModel(iRequset,arge)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data listData) throws Exception {
                        if (listData.status.equals("0000")){
                            dataColl.succeed(listData.result);
                        }else {
                            dataColl.failure(listData);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        dataColl.failure(new Data(throwable.getMessage()));
                    }
                });

    }
    protected abstract Observable getModel(IRequset iRequset, Object...arge);
    //销毁
    public void destroy(){
        dataColl=null;
    }
}
