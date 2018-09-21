package com.example.demo.Entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application-person.yml"})
@ConfigurationProperties(prefix = "people")
public class People {

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
