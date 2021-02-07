package com.hewking.demo.string;

public class SplitStringTest {

    public static void main(String[] args) {

        String test = "https://www.flowchat.me/g/1";

        int pos = test.lastIndexOf('/');
        if (test.length() > pos + 1) {
            System.out.println(test.substring(pos + 1));
        }

        String test2 = "12345";
        System.out.print(test2.substring(0, 2));
    }

}
