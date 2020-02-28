package com.study.boke.Exception;

public enum AllException implements IBokeCustomerException {


    NOT_FOUND_QUESTION(41,"要找的问题被删除或者不存在了"),
    HAVE_NO_POWER(42,"没有权限做此操作"),
    GITHUB_CONNECT_FAILD(43,"github服务器繁忙,请稍后换个姿势重来"),
    HAVE_NO_PAGE(44,"你要找的页数不存在"),
    HAVE_NO_LOGIN(45,"未登录，做个登录呗");


    private Integer code;
    private String msg;

    AllException(Integer code,String msg){
        this.code=code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getCode(){
        return code;
    }
}
