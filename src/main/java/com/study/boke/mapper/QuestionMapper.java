package com.study.boke.mapper;

import com.study.boke.dto.QuestionDTO;
import com.study.boke.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> listForId(Integer id, Integer offset, Integer size);

    @Select("select count(1) from question where creator=#{id}")
    Integer countForId(Integer id);

    @Select("select * from question where id=#{id}")
    Question listForQuestionId(Integer id);



}
