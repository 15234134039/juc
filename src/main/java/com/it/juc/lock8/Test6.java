package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 6、两个对象！增加两个静态的同步方法，先打印发短信？打电话？//发短信
 */
public class Test6 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone6 phone1 = new Phone6();
        Phone6 phone2 = new Phone6();

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

class Phone6 {

    /**
     * synchronized 锁的对象是方法的调用者！
     * static 静态方法，类一加载就有了，锁的是Class
     */
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");
    }
}
