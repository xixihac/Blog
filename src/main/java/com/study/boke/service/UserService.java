package com.study.boke.service;

import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User UserExist(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies!=null){
            for(Cookie token:cookies){
                if ("token".equals(token.getName())){
                    user = userMapper.findByCookie(token.getValue());
                    request.getSession().setAttribute("githubUser",user);
                }
            }
        }
        return user;
    }
}
