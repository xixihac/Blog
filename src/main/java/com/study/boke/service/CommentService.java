package com.study.boke.service;

import com.study.boke.Exception.AllException;
import com.study.boke.dto.CommentDTO;
import com.study.boke.dto.ResultDTO;
import com.study.boke.mapper.CommentMapper;
import com.study.boke.mapper.QuestionMapper;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.Comment;
import com.study.boke.model.CommentExample;
import com.study.boke.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

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

    public List<CommentDTO> listForQuestionId(Integer id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<Comment> comments = commentMapper.selectByExample(example);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            User user = userMapper.selectByPrimaryKey(comment.getCommentator());
            commentDTO.setUser(user);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }
}
