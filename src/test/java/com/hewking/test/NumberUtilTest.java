package com.hewking.test;

import com.hewking.demo.basic.NumberUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Classname NumberUtilTest
 * @Description TODO
 * @Date 2021/2/10 5:20 下午
 * @Created by jianhao
 */
public class NumberUtilTest {

    @Test
    public void testMax(){
        List<String> words = Arrays.asList("hello", "world");
        // 返回异常值
        String str = NumberUtil.max(words).orElse("no word ..");
        NumberUtil.max(words).orElseThrow(IllegalArgumentException::new);
        String s = NumberUtil.max(words).get();

    }

    private static Random random = new Random();

    private static int random(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("参数小于0!");
        }
        return Math.abs(random.nextInt(n)) % n;
    }

    @Test
    public void testRandom() {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++)
            if (random(n) < n/2)
                low++;
        System.out.println(low);
    }

}
