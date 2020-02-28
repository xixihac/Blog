package com.study.boke.Exception;

public class BokeCustomerException extends RuntimeException {
    private String msg;
    private Integer code;


    public BokeCustomerException(AllException ex) {
        this.code=ex.getCode();
        this.msg=ex.getMsg();
    }

    public String getMsg(){
        return msg;
    }
    public Integer getCode(){
        return code;
    }

}
