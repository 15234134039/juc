package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8、1个静态的同步方法，1个普通的同步方法，两个对象，先打印发短信？打电话//打电话
 * 2把锁
 */
public class Test8 {
    public static void main(String[] args) {

        Phone8 phone1 = new Phone8();
        Phone8 phone2 = new Phone8();

        new Thread(()->{
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        }, "B").start();
    }
}

class Phone8 {

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
