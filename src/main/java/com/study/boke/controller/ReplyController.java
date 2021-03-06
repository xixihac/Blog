package com.study.boke.controller;

import com.study.boke.Exception.AllException;
import com.study.boke.Exception.BokeCustomerException;
import com.study.boke.dto.ReplyDTO;
import com.study.boke.model.User;
import com.study.boke.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;


    @GetMapping("/reply")
    public String reply(Model model, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("githubUser");
        if (user == null) {
            throw new BokeCustomerException(AllException.HAVE_NO_LOGIN);
        }

        List<ReplyDTO> replyDTOList = replyService.list(user.getId());
        Collections.reverse(replyDTOList);
        replyService.emptyRead(user.getId());
        model.addAttribute("replies",replyDTOList);



        return "reply";
    }
}
