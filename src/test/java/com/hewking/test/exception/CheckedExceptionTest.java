package com.hewking.test.exception;

import org.junit.Test;

import java.util.Optional;

/**
 * @Classname CheckedExceptionTest
 * @Description TODO
 * @Date 2021/2/17 3:01 下午
 * @Created by jianhao
 */
public class CheckedExceptionTest {

    private static class TheCheckedException extends Exception {

        private String message;

        TheCheckedException(String message){
            this.message = message;
        }

    }

    @Test
    public void test(){
        try {
            foo();
        } catch (TheCheckedException e) {
            e.printStackTrace();
        }
    }

    void foo() throws TheCheckedException{
        throw new TheCheckedException("checked exception");
    }

    Optional<Void> quz(){
        return Optional.empty();
    }

}
