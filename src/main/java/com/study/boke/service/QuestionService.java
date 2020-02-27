package com.study.boke.service;

import com.study.boke.dto.PaginationDTO;
import com.study.boke.dto.QuestionDTO;
import com.study.boke.mapper.QuestionMapper;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.Question;
import com.study.boke.model.QuestionExample;
import com.study.boke.model.User;
import org.apache.ibatis.session.RowBounds;
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

        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        //往QuestionDTO中加User
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestionDTOS(questionDTOList);

        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagnation(totalCount,page,size);

        return paginationDTO;

    }

    public PaginationDTO listForId(Integer id, Integer page, Integer size) {
        Integer offset = size*(page-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {

            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestionDTOS(questionDTOList);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(example);
        paginationDTO.setPagnation(totalCount,page,size);

        return paginationDTO;
    }

    public QuestionDTO listForQuestionId(Integer id) {


        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question){
        if (question.getId()==null){
            //新问题  添加
            questionMapper.insert(question);

        }else {

            Question idQuestion = questionMapper.selectByPrimaryKey(question.getId());

            //老问题 更新
            idQuestion.setTitle(question.getTitle());
            idQuestion.setDescription(question.getDescription());
            idQuestion.setTag(question.getTag());
            idQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(idQuestion.getId());
            questionMapper.updateByExampleSelective(idQuestion, questionExample);

        }
    }
}
