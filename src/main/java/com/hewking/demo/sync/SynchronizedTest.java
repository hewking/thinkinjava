package com.hewking.demo.sync;

public class SynchronizedTest {

    public static void main(String[] args) {

    }

    public void test() {
        synchronized (this) {

            System.out.println("synchronized method");
        }
    }

}
