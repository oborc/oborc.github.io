package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.config.CacheManagementConfigUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CacheManager myCacheManager;

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public void insertTest(int age,String name)
    {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        userMapper.insert(user);
    }

    public void seletAllTest()
    {
        List<User> users = userMapper.selectAll();
        for(User user : users)
        {
            System.out.println(user.getName());
        }
    }

    @Cacheable(value = "testCache",key = "#age+':'+#name",cacheManager = "myCacheManager")
    public User selectOne(Integer age,String name)
    {
        LOG.info("getting from db...");
        User user = new User();
        user.setAge(age);
        Cache cache=myCacheManager.getCache("testCache");
        Object use = cache.getNativeCache();
        User user1 = cache.get(12 +":"+"test2",User.class);
        return userMapper.selectOne(user);
    }

}

