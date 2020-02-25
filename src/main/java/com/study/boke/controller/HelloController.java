package com.study.boke.controller;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.dto.QuestionDTO;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
import com.study.boke.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "1") Integer size){
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

        PaginationDTO questionDTOList = questionService.list(page,size);
        model.addAttribute("pageId",page);
        model.addAttribute("questions",questionDTOList);

//        if (user == null){
//            return "index";
//        }


        return "index";
    }
}
