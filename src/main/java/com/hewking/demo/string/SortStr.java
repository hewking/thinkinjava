package com.hewking.demo.string;

import kotlin.collections.CollectionsKt;

import java.util.Comparator;
import java.util.List;

public class SortStr {
    public static void main(String[] args) {
        List<String> list = CollectionsKt.arrayListOf("orojtoj", "abc", "abcde", "sdfsf", "psdfs");

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len1 = o1.length();
                int len2 = o2.length();

                int len = Math.min(len1, len2);

                char[] array1 = o1.toCharArray();
                char[] array2 = o2.toCharArray();

                for (int i = 0; i < len; i++) {
                    if (array1[i] != array2[i]) {
                        return array1[i] - array2[i];
                    }
                }

                return len1 - len2;
            }
        };

        list.sort(comparator);

        System.out.println(list);


    }
}
