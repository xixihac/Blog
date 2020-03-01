package com.study.boke.mapper;

import com.study.boke.model.Question;
import com.study.boke.model.QuestionExample;

import java.util.List;

public interface QuestionMapperExt {
    int incView(Integer id);
    int incComment(Integer id);
    List<Question> listForSearch(String search,Integer page, Integer size);
    long countBySearch(String search);
}