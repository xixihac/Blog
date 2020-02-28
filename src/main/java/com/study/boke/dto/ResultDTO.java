package com.study.boke.dto;

import com.study.boke.Exception.AllException;
import com.study.boke.Exception.BokeCustomerException;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String msg;

    public static ResultDTO errorOf(Integer code,String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }

    public static ResultDTO errorOf(AllException aException){
        return errorOf(aException.getCode(),aException.getMsg());
    }

    public static ResultDTO successOf(Integer code,String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }
    public static ResultDTO successOf() {

        return successOf(200,"成功回复");

    }
}
