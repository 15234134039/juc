package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1、标准情况下，两个线程先打印发短信还是打电话？ 发短信
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSms();
        }, "A").start();

        new Thread(()->{
            phone.call();
        }, "B").start();
    }


}

class Phone {
    /**
     * synchronized 锁的对象是方法的调用者！
     * 两个方法用的是同一个锁，谁先拿到谁执行！
     */
    public synchronized void sendSms(){
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}
