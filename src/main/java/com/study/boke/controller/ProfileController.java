package com.study.boke.controller;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.model.User;
import com.study.boke.service.QuestionService;
import com.study.boke.service.ReplyService;
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

    @Autowired
    ReplyService replyService;

    @GetMapping("/profile/{action}")
    public String profile(Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("githubUser");
        if (user == null) {
            return "redirect:/";
        }

            PaginationDTO questionDTOList = questionService.listForId(user.getId(),page,size);
            model.addAttribute("pageId",page);
            model.addAttribute("questions",questionDTOList);

            Integer count = replyService.countRead(user.getId());
            model.addAttribute("count",count);




        return "profile";

    }
}
