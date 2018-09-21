package com.example.demo.TestClassLoader;

public class A {

    void one() {
        System.out.println("one");
    }

    void two() {
        System.out.println("two");
    }

    static void three() {
        System.out.println("three");
    }

    void four() {
        System.out.println("four");
    }

    public void test()
    {
        this.one();
        this.two();
        this.three();
        this.four();
    }

}
