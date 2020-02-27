package com.study.boke.Exception;

public class BokeCustomerException extends RuntimeException {
    private String msg;

    public BokeCustomerException(String message) {
        this.msg = message;
    }

    public BokeCustomerException(AllException ex) {
        this.msg=ex.getMsg();
    }

    public String getMsg(){
        return msg;
    }

}
