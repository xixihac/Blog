package com.study.boke.controller;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.model.User;
import com.study.boke.service.QuestionService;
import com.study.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(Model model,
                          @PathVariable("action") String action,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "1") Integer size) {
        User user = userService.UserExist(request);
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "我的问题");
            PaginationDTO questionDTOList = questionService.listForId(user.getId(),page,size);
            model.addAttribute("pageId",page);
            model.addAttribute("questions",questionDTOList);

        } else if ("replies".equals(action)) {
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "最新回复");

        }


        return "profile";

    }
}
