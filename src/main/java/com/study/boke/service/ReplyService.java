package com.study.boke.service;

import com.study.boke.dto.ReplyDTO;
import com.study.boke.mapper.*;
import com.study.boke.model.Comment;
import com.study.boke.model.Question;
import com.study.boke.model.Reply;
import com.study.boke.model.ReplyExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    ReplyMapperExt replyMapperExt;

    public List<ReplyDTO> list(Integer id) {


        ReplyExample example = new ReplyExample();
        example.createCriteria().andReceiverEqualTo(id);
        List<Reply> replies = replyMapper.selectByExample(example);
        List<ReplyDTO> replyDTOS = new ArrayList<>();
        for (Reply reply : replies) {
            ReplyDTO replyDTO = new ReplyDTO();

            replyDTO.setSenderUser(userMapper.selectByPrimaryKey(reply.getSender()));
            replyDTO.setQuestion(questionMapper.selectByPrimaryKey(reply.getQuestionId()));
            BeanUtils.copyProperties(reply,replyDTO);
            replyDTOS.add(replyDTO);
        }

        return replyDTOS;

    }

    public void insert(Comment comment) {
        Reply reply = new Reply();


        if (comment.getType()==0){
            //回复
            reply.setQuestionId(comment.getParentId());
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            reply.setReceiver(question.getCreator());
            reply.setSender(comment.getCommentator());
            reply.setType(0);
            reply.setIsRead(0);
            reply.setGmtCreate(System.currentTimeMillis());
        }else {
            //评论
            reply.setQuestionId(comment.getParentId());
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getType());
            reply.setReceiver(comment1.getCommentator());
            reply.setSender(comment.getCommentator());
            reply.setType(1);
            reply.setIsRead(0);
            reply.setGmtCreate(System.currentTimeMillis());
        }

        if(!reply.getSender().equals(reply.getReceiver())){
            replyMapper.insert(reply);
        }


    }

    public Integer countRead(Integer id) {

        long l = replyMapperExt.countNoReadByReId(id);

        return Integer.valueOf((int) l);

    }

    public void emptyRead(Integer id) {
        replyMapperExt.emptyRead(id);
    }
}
