package com.study.boke.service;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.dto.QuestionDTO;
import com.study.boke.mapper.QuestionMapper;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.Question;
import com.study.boke.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size){
        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.list(offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestionDTOS(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagnation(totalCount,page,size);

        return paginationDTO;

    }
}
