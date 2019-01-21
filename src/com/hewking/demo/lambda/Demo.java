package com.hewking.demo.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(() -> {

        });

        //第一种 直接匿名内部类
        List<String> list = new ArrayList<>();
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        //第二种 lambda 表达式
        Collections.sort(list,(o1,o2) -> {
            return o1.compareTo(o2);
        });

        // 第三种 :: 操作符
        Collections.sort(list,String::compareTo);

    }

}
