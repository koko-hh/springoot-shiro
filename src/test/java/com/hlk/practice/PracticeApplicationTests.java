package com.hlk.practice;


import com.hlk.practice.mapper.PeopleMapper;
import com.hlk.practice.pojo.People;
import com.hlk.practice.service.PeopleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public  class PracticeApplicationTests {
    @Autowired
    PeopleMapper peopleMapper;

    @Test
    public void contextLoads() {
    }

}
