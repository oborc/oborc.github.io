package com.example.demo;

import com.example.demo.Entity.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    People perso;

    @Test
    public void pring()
    {
        System.out.println(perso.getName());
    }
    @Test
    public void contextLoads() {
    }

}
