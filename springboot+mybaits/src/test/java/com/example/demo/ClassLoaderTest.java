package com.example.demo;

import com.example.demo.TestClassLoader.B;
import org.junit.Test;

public class ClassLoaderTest
{
    @Test
    public void test()
    {
        B b = new B();
        b.test();
        B.three();
    }
}
