package com.hewking.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 测试 java8 stream api
 */
public class Demo1 {

    @Test
    public void test(){

    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "Lisa", "left", "for", "school", "at", "senven", "o'clock");
//        testFilter(com.hewking.demo.collection);

//        testMap(com.hewking.demo.collection);

//        testReduce();

//        testFilterToList();
    }

    private static void testFilter(List<String> list) {
        list.stream()
                .filter((s) -> s.compareTo("hello") != 0)
                .forEach(System.out::println);
    }

    public static void testMap(List<String> list) {
        Predicate<String> p1 = (s) -> s.compareTo("hello") != 0;
        Predicate<String> p2 = (s) -> !s.startsWith("L");
        list.stream().filter(p1.and(p2))
                .map((s) -> "hello " + s)
                .forEach(System.out::println);
    }

    private static void testReduce() {
        float count = Arrays.asList(300, 45, 777, 444, 444, 222, 333).stream().map((n) -> n * 1.2f).reduce((u, v) -> u + v).get();
        System.out.println(count);
    }

    /*
    注意 collect 的用法，生成一个新的集合，全是符合新的过滤规则的
     */
    @Test
    public void testFilterToList() {
        Arrays.asList("aaa", "ccc", "aa", "cc", "ab", "abc", "dddd").stream().filter((s) -> s.length() > 2).collect(Collectors.toList())
                .forEach(System.out::print);
    }

    /**
     * 对列表中的每一个元素应用函数
     */
    @Test
    public void testMapForElement() {
        List<String> list = Arrays.asList("a", "b", "c", "c");
        String str = list.stream().map((s) -> s.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(str);
    }

    /**
     * 对元素进行去重
     */
    @Test
    public void testDistinct() {
        List<String> list = Arrays.asList("a", "b", "c", "c", "d", "a");
        String str = list.stream().map(s -> s.toUpperCase()).distinct().collect(Collectors.joining(","));
        System.out.println(str);
    }


    /**
     * 计算集合元素的最大，最小，总和，平均值
     */
    @Test
    public void testSummaryStaistics() {
        List<Integer> list = Arrays.asList(43, 23, 56, 87, 2, 656, 65, 32, 96, 43, 37, 1);
        IntSummaryStatistics statistics = list.stream().mapToInt(t -> t).summaryStatistics();
        System.out.println("max : " + statistics.getMax());
        System.out.println("min : " + statistics.getMin());
        System.out.println("sum : " + statistics.getSum());
        System.out.println("avarage : " + statistics.getAverage());
    }


}
