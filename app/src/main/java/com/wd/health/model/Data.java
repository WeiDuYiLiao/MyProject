package com.wd.health.model;

/*时间:2020/1/8
创建人:yang 
创建人:杨靖宇*/
public class Data<T> {
    /*    "message": "登陆成功",
            "result": {
        "status": "0000"*/
    public String message;
    public String status;
    public T result;



    public Data(String message) {
        this.message = message;
    }
}
