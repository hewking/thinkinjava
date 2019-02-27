package com.hewking.demo.math;

public class MathTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 5;

        int x = -2;
// 总结就是 ，跟最小值取最大值，跟最大值取最小值
        System.out.println(Math.max(a,Math.min(b,x)));
        System.out.println(Math.min(b,Math.max(x,a)));
        System.out.println(Math.max(Math.min(b,x),a));
        System.out.println(Math.min(Math.max(x,a),b));
    }
}
