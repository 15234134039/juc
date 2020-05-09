package com.it.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3、增加了一个普通方法后！先执行发短信还是Hello？普通方法
 */
public class Test3 {

    public static void main(String[] args) {
        Phone3 phone = new Phone3();

        new Thread(()->{
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.hello();
        }, "B").start();
    }



}

class Phone3 {

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

    /**
     * 这里没有锁！不是同步方法，不受锁的影响
     */
    public void hello(){
        System.out.println("hello");
    }
}