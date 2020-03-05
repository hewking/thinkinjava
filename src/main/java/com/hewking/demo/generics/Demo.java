package com.hewking.demo.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * demo 事例 泛型数组 初始化问题
 * <p>
 * java 中不能直接初始化具体的泛型类型  需要初始化的好时机在 运行时 优雅在反射中
 */
public class Demo {

    public static void main(String[] args) {

//        List<String>[] com.hewking.demo.collection = new List<String>[10]; 错误
        List<?>[] list = new List<?>[10];// 可以
        list[0] = new ArrayList<Integer>();
        list[1] = new ArrayList<String>();
        ArrayList<String> a = (ArrayList<String>) list[0]; // 显示强转

        String s = findviewById(0);


        GenericArray<String> genericArray = new GenericArray<>(String.class, 5);
    }

    public static <T> void gengeric() {
//        T[] array = new T[10]; 错

    }

    public static <T> T findviewById(int layoutid) {
        Object o = new Object();
        return (T) o;
    }

    public static <T> T newinstence(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();

    }

}
