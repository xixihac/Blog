package com.study.boke.controller;

import com.study.boke.dto.QuestionDTO;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
import com.study.boke.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model){
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
        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions",questionDTOList);

        return "index";
    }
}
