package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void insertTest(int age,String name)
    {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        userMapper.insert(user);
    }

    public void seletAll()
    {
        List<User> users = userMapper.selectAll();
        for(User user : users)
        {
            System.out.println(user.getName());
        }
    }

}

