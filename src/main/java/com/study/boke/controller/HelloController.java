package com.study.boke.controller;

import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies!=null){
            for(Cookie token:cookies){
                if ("token".equals(token.getName())){
                    user = userMapper.findByCookie(token.getValue());
                    System.out.println(user);
                    request.getSession().setAttribute("githubUser",user);
                }
            }
        }

        if (user == null){
            return "index";
        }

        return "index";
    }
}
