package com.minjihong;

import com.minjihong.never.admin.mapper.UserMapper;
import com.minjihong.never.admin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NeverFrameworkApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("=============select all method test start======================");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

}
