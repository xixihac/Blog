package com.study.boke;

import com.study.boke.mapper.QuestionMapper;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.Question;
import com.study.boke.model.QuestionExample;
import com.study.boke.model.User;
import com.study.boke.model.UserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BokeApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void contextLoads() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        Map<String, Object> map = list.get(0);
        System.out.println(map);

    }
//    @Test
//    void test(){
//
//        Question question = questionMapper.selectByPrimaryKey(1);
//        System.out.println(question.getDescription());
//    }

    @Test
    void test(){
        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo(1);
        List<Question> questionList = questionMapper.selectByExample(example);
        Question user =questionList.get(0);
        System.out.println(user.getDescription());
    }

}
