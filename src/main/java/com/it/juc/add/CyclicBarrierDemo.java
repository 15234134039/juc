package com.it.juc.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("集齐7个啦");
        });

        for (int i = 1; i <= 7; i++){

            int temp = i;

            // lambda不能操作到 i
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "集齐" + temp);
                try {
                    cyclicBarrier.await();  // 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
