package com.study.boke.controller;

import com.study.boke.dto.QuestionDTO;
import com.study.boke.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionControoler {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(Model model,
                           @PathVariable("id")Integer id){
        QuestionDTO questionDTO = questionService.listForQuetionId(id);

        model.addAttribute("question",questionDTO);
        return "question";
    }
}
