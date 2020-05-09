package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7、1个静态的同步方法，1个普通的同步方法，一个对象，先打印发短信？打电话？//打电话
 * 2把锁
 */
public class Test7 {
    public static void main(String[] args) {

        Phone7 phone = new Phone7();

        new Thread(()->{
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        }, "B").start();
    }
}

class Phone7 {

    /**
     * 静态的同步方法锁的是 Class 类模板
     */
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    /**
     * 普通的同步方法锁的调用者
     */
    public synchronized void call(){
        System.out.println("打电话");
    }
}
