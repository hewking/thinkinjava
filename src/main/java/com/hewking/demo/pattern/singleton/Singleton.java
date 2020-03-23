package com.hewking.demo.pattern.singleton;

public class Singleton {

    private Singleton() {
        System.out.println("Singleton init");
    }

    private static class Holder {
        private static Singleton sInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.sInstance;
    }


}
