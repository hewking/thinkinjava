package com.hewking.dynamicproxy.impl;

import com.hewking.dynamicproxy.UserService;

public class MyUserService implements UserService {
    @Override
    public String getName() {
        return "my name";
    }

    @Override
    public int getAge(int age) {
        return age;
    }
}
