package com.study.boke.advice;

import com.study.boke.Exception.BokeCustomerException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handlerControllerException(Model model, Throwable ex){
        if (ex instanceof BokeCustomerException){
            model.addAttribute("msg",(((BokeCustomerException) ex).getMsg()));
        }else {
        model.addAttribute("msg","没准是服务器烧坏了~");
        }
        return new ModelAndView("error");

    }
}
