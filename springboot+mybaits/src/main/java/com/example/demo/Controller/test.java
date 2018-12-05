package com.example.demo.Controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class test {

    @Resource
    private UserService userService;
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public User getOne(@RequestParam("age") Integer age,@RequestParam("name") String name){
        return userService.selectOne(age, name);
    }

    @RequestMapping(value = "/testInsert",produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestParam("name") String name, @RequestParam("age") Integer age){
        userService.insertTest(age,name);
    }
}
