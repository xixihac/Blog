package com.study.boke.controller;

import com.study.boke.dto.ReplyDTO;
import com.study.boke.model.User;
import com.study.boke.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;


    @GetMapping("/reply")
    public String reply(Model model, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("githubUser");
        List<ReplyDTO> replyDTOList = replyService.list(user.getId());
        replyService.emptyRead(user.getId());
        model.addAttribute("replies",replyDTOList);



        return "reply";
    }
}
