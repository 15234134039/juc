package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 两个对象，两个同步方法，发短信还是打电话？ // 打电话
 */
public class Test4 {
    public static void main(String[] args) {

        // 两个对象，两个调用者，两把锁！
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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

class Phone4 {

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}