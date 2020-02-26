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

    public void createOrUpdate(User user) {
        User accountIdUser = userMapper.getByAccountId(user.getAccountId());
        if(accountIdUser == null){
            //用户为新用户  添加
            userMapper.insert(user);
        }else {
            //用户为老用户 更新
            accountIdUser.setName(user.getName());
            accountIdUser.setAvatarUrl(user.getAvatarUrl());
            accountIdUser.setToken(user.getToken());
            accountIdUser.setGmtModified(System.currentTimeMillis());
            userMapper.update(accountIdUser);

        }

    }
}
