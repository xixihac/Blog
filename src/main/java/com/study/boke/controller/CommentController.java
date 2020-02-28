package com.study.boke.controller;

import com.study.boke.Exception.AllException;
import com.study.boke.dto.CommentDTO;
import com.study.boke.dto.ResultDTO;
import com.study.boke.mapper.CommentMapper;
import com.study.boke.model.Comment;
import com.study.boke.model.User;
import com.study.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request){


        User githubUser = (User) request.getSession().getAttribute("githubUser");
        if (githubUser==null){
            return ResultDTO.errorOf(AllException.HAVE_NO_LOGIN);
        }


        ResultDTO result =(ResultDTO) commentService.insert(commentDTO);


        return result;
    }
}
