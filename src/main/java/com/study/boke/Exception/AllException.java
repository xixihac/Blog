package com.study.boke.Exception;

public enum AllException implements IBokeCustomerException {


    NOT_FOUND_QUESTION("要找的问题被删除或者不存在了"),HAVE_NO_POWER("没有权限做此操作");


    private String msg;

    AllException(String msg){
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
