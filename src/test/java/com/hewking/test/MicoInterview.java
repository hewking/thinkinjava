package com.hewking.test;

import org.junit.Test;

/**
 * @Classname MicoInterview
 * Mico公司的笔试题
 * @Description TODO
 * @Date 2020-05-06 23:31
 * @Created by jianhao
 */
public class MicoInterview {

    /**
     * 第一题，Integer.valueof的问题
     */
    @Test
    public void test1() {
        Integer a = Integer.valueOf("2");
        Integer b = Integer.valueOf("2");
        System.out.println(a == b);
    }

    @Test
    public void test2() {
        String str = "aa";
        String str2 = new String("aa");
        String str3 = str.intern();
        System.out.println(str == str3);
    }

    @Test
    public void test3() {
        String str = "aa";
        char[] chs = {'a', 'b', 'c'};
        change(str, chs);
        System.out.print(str);
        System.out.print(chs);
    }

    void change(String str, char[] chs) {
        str = "bb";
        chs[0] = 'f';
    }

    static class A {
        static {
            System.out.println("1");
        }

        A() {
            System.out.println("2");
        }

    }

    static class B extends A {
        static {
            System.out.println("3");
        }

        B() {
            System.out.println("4");
        }

        B(int a) {
            System.out.println("a:" + a);
            System.out.println("5");
        }
    }

    @Test
    public void test4() {
        B b = new B(2);
        b = new B(2);
    }

}
