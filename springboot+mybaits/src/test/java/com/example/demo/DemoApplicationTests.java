package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)

public class DemoApplicationTests {

    @Resource
    private UserService userService;
    @Test
    public void testMapper() {
        userService.seletAllTest();
    }



}
