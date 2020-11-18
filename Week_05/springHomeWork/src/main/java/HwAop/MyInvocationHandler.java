package HwAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */

public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("tuning");
        Object result = method.invoke(target, args);
        System.out.println("take a rest");
        return result;
    }
}