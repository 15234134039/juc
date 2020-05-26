package com.it.juc.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore：信号量
 *
 * 抢车位！6车---3个停车位置
 *
 * semaphore.acquire()获得，假设如果已经满了，等待，等待被释放为止！
 * semaphore.release();释放，会将当前的信号量释放 + 1，然后唤醒等待的线程！
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量：停车位! 限流！
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // acquire() 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();    // release() 释放
                }
            }, String.valueOf(i)).start();
        }
    }
}
