package com.study.boke.controller;

import com.study.boke.Exception.AllException;
import com.study.boke.Exception.BokeCustomerException;
import com.study.boke.dto.PaginationDTO;
import com.study.boke.service.QuestionService;
import com.study.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class HelloController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String hello(Model model,
                        @RequestParam(name="search",defaultValue = "") String search,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        if(!"".equals(search.trim())){

            PaginationDTO questionDTOList = questionService.listForSearch(page, size,search);
            if (page>questionDTOList.getTotalPage() || page < 1 ){
                throw new BokeCustomerException(AllException.HAVE_NO_PAGE);
            }
            model.addAttribute("pageId", page);
            model.addAttribute("questions", questionDTOList);
            model.addAttribute("searchName",search);

            return "index";
        }


        PaginationDTO questionDTOList = questionService.list(page, size);
        if (page>questionDTOList.getTotalPage() || page < 1 ){
            throw new BokeCustomerException(AllException.HAVE_NO_PAGE);
        }

        model.addAttribute("pageId", page);
        model.addAttribute("questions", questionDTOList);

//        if (user == null){
//            return "index";
//        }


        return "index";
    }

}
