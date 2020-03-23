package com.hewking.test;

import com.hewking.demo.pattern.singleton.Singleton;
import org.junit.Test;

/**
 * lambda 表达式vs 匿名类
 */
public class Demo2 {

    @Test
    public void test() {
        Singleton.getHolder();
    }

}
