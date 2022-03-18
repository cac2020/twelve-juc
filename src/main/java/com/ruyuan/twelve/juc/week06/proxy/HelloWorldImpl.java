package com.ruyuan.twelve.juc.week06.proxy;

public class HelloWorldImpl implements HelloWorld {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
