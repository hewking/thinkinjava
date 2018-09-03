package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserInvocationHandler<T> implements InvocationHandler {

    private Object target;

    public UserInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getName".equals(method.getName())) {
            System.out.println("invoke before");
            Object result = method.invoke(target,args);
            System.out.println("invoke after");
            return result;
        } else {
            return method.invoke(target,args);
        }
    }
}
