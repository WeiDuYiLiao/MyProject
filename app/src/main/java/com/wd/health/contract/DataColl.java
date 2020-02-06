package com.wd.health.contract;

import com.wd.health.model.Data;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public interface DataColl<T> {
    void  succeed(T res);
    void  failure(Data data);
}
