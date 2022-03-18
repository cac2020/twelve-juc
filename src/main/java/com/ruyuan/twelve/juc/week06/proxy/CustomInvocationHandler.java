package com.ruyuan.twelve.juc.week06.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CustomInvocationHandler implements InvocationHandler {
    private HelloWorld target;
    private ExecutorService scheduler;
    public CustomInvocationHandler(HelloWorld target, ExecutorService scheduler) {
        this.target = target;
        this.scheduler = scheduler;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Callable<Object> methodRequest = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(123);
                return null;
            }
        };
        Future<Object> futrue = scheduler.submit(methodRequest);
        return futrue;
    }
}
