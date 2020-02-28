package com.study.boke.service;

import com.study.boke.Exception.AllException;
import com.study.boke.dto.CommentDTO;
import com.study.boke.dto.ResultDTO;
import com.study.boke.mapper.CommentMapper;
import com.study.boke.mapper.QuestionMapper;
import com.study.boke.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionService questionService;

    public Object insert(CommentDTO commentDTO) {


        if (commentDTO.getParentId()==null || questionService.isIdNull(commentDTO.getParentId())){
            return ResultDTO.errorOf(AllException.NOT_FOUND_QUESTION);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContext(commentDTO.getContext());
        comment.setCommentator(commentDTO.getCommentator());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);

        questionService.incComment(commentDTO.getParentId());

        return ResultDTO.successOf();

    }
}
