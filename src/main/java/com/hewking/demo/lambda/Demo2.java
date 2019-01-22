package com.hewking.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Collection stream api demo
 */
public class Demo2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "Lisa", "left", "for", "school", "at", "senven", "o'clock");
//        testFilter(list);

//        testMap(list);

//        testReduce();

        testFilterToList();
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
    private static void testFilterToList() {
        Arrays.asList("aaa", "ccc", "aa", "cc", "ab", "abc", "dddd").stream().filter((s) -> s.length() > 2).collect(Collectors.toList())
                .forEach(System.out::print);
    }


}
