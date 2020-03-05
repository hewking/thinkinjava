package com.hewking.demo.dynamicproxy;

import com.hewking.demo.dynamicproxy.UserInvocationHandler;
import com.hewking.demo.dynamicproxy.UserService;
import com.hewking.demo.dynamicproxy.impl.MyUserService;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        UserService userService = new MyUserService();
        UserService service = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), new UserInvocationHandler<>(userService));
        System.out.println(service.getName());
        System.out.println(service.getAge(10));
    }
}
