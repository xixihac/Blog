package com.study.boke.controller;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.service.QuestionService;
import com.study.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {


        PaginationDTO questionDTOList = questionService.list(page, size);
        model.addAttribute("pageId", page);
        model.addAttribute("questions", questionDTOList);

//        if (user == null){
//            return "index";
//        }


        return "index";
    }
}
