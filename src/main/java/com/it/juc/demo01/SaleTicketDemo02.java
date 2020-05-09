package com.it.juc.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {

    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket02 ticket = new Ticket02();

        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}

class Ticket02 {

    private int number = 100;

    Lock lock = new ReentrantLock();


    public void sale(){
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了一张票，剩余" + --number) ;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
