package com.study.boke;

import com.study.boke.mapper.UserMapper;
import com.study.boke.model.User;
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

    @Test
    void contextLoads() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        Map<String, Object> map = list.get(0);
        System.out.println(map);

    }
    @Test
    void test(){
        jdbcTemplate.execute("insert into user(name) values('战后高三')");
    }

}
