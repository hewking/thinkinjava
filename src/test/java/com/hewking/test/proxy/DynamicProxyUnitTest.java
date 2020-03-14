package com.hewking.test.proxy;

import com.hewking.demo.dynamicproxy.UserInvocationHandler;
import com.hewking.demo.dynamicproxy.UserService;
import com.hewking.demo.dynamicproxy.impl.MyUserService;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * @Classname DynamicProxyUnitTest
 * @Description TODO
 * @Date 2020-03-14 23:08
 * @Created by jianhao
 */
public class DynamicProxyUnitTest {

    @Test
    public void testNewProxyInstance(){
        UserService userService = new MyUserService();
        UserService service = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader()
                , userService.getClass().getInterfaces()
                , new UserInvocationHandler<>(userService));
        System.out.println(service.getName());
        System.out.println(service.getAge(10));
    }

    @Test
    public void testGetProxyClass(){
        UserService target = new MyUserService();
        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(),target.getClass().getInterfaces());
        try {
            Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
            UserService proxyclassImpl = (UserService) constructor.newInstance((InvocationHandler) (proxy, method, args) -> {
                    System.out.println("invoke before");
                 Object result = method.invoke(target,args);
                 System.out.println("invoke after");
                 return result;
            });


            proxyclassImpl.getName();


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试类似Retrofit中，只是对于代理中target 不是一个普通对象
     * 而是Class对象作为代理中targe对象的情况。、
     */
    @Test
    public void testClassProxy(){
        // 这里通过改变UserService的getName 返回hello world作为测试
        UserService service = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader()
                ,new Class<?>[]{UserService.class}
                ,new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getName")) {
                    return "hello world";
                }
                return new Object();
            }
        });

        System.out.println(service.getName());
    }

}
