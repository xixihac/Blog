package com.study.boke.controller;

import com.study.boke.Exception.AllException;
import com.study.boke.Exception.BokeCustomerException;
import com.study.boke.dto.QuestionDTO;
import com.study.boke.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(Model model,
                           @PathVariable("id")Integer id){
        QuestionDTO questionDTO = null;

        try {
            questionDTO = questionService.listForQuestionId(id);
        }catch (Exception e){
            throw new BokeCustomerException(AllException.NOT_FOUND_QUESTION);
        }

        model.addAttribute("question",questionDTO);

        questionService.incView(questionDTO.getId());
        return "question";
    }
}
