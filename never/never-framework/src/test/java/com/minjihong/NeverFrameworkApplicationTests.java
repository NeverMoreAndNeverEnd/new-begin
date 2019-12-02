package com.minjihong;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.admin.mapper.UserMapper;
import com.minjihong.never.admin.oss.AliyunOss;
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

    @Autowired
    private AliyunOss aliyunOss;

    @Test
    public void testOss() {
        aliyunOss.quickStart();

    }

    @Test
    public void testSelect() {
        System.out.println("=============select all method test start======================");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("axx");
        user.setAge(23);
        user.setEmail("axx@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    @Test
    public void testPage() {
        //当前页,每页记录数
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page, null);
        List<User> records = page.getRecords();//每页数据
        long current = page.getCurrent();//当前页
        long size = page.getSize();//每页记录数
        long total = page.getTotal();//总记录数
        long pages = page.getPages();//总页数
        boolean b = page.hasPrevious();//是否有上一页
        boolean b1 = page.hasNext();//是否有下一页

        System.out.println(records);
        System.out.println(current);
        System.out.println(size);
        System.out.println(total);
        System.out.println(pages);
        System.out.println(b);
        System.out.println(b1);
    }

    @Test
    public void testDelete() {
        /*User user = new User();
        user.setName("yyf");
        user.setAge(32);
        user.setEmail("yyf@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);*/

        int i = userMapper.deleteById(1199564733150150657L);
        System.out.println(i);

    }

}
