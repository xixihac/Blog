package com.study.boke.service;

import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
import com.study.boke.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void UserExist(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie token : cookies) {
                if ("token".equals(token.getName())) {


                    String tokenValue = token.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(tokenValue);
                    List<User> user = userMapper.selectByExample(userExample);
                    if (user.size() != 0) {
                        request.getSession().setAttribute("githubUser", user.get(0));
                    }
                }
            }
        }
    }

    public void createOrUpdate(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size()==0) {
            //用户为新用户  添加
            userMapper.insert(user);
        } else {
            User accountIdUser = users.get(0);
            //用户为老用户 更新
            accountIdUser.setName(user.getName());
            accountIdUser.setAvatarUrl(user.getAvatarUrl());
            accountIdUser.setToken(user.getToken());
            accountIdUser.setGmtModified(System.currentTimeMillis());
            UserExample userUpdateExample = new UserExample();
            userUpdateExample.createCriteria().andIdEqualTo(accountIdUser.getId());
            userMapper.updateByExampleSelective(accountIdUser,userUpdateExample);

        }

    }
}
