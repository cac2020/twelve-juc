package com.ruyuan.twelve.juc.week06.proxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProxyTest {

    public static void main(String[] args) {
        ThreadPoolExecutor scheduler = new ThreadPoolExecutor(1,
                3,
                60 * 60,
                TimeUnit.SECONDS,
                // 模式角色的：ActionObject.Scheduler
                new ArrayBlockingQueue<>(200),
                r -> new Thread(r, "AsyncRequestPersistence"));

        // scheduler 拒绝策略
        // 谁来提交，被拒绝后谁执行当前任务
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl(),scheduler);
        HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(
                HelloWorld.class.getClassLoader(),
                new Class[]{HelloWorld.class},
                handler);
        proxy.sayHello("Mikan");

    }
}
