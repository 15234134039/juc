package com.it.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("c"));

        System.out.println("===========");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，没有异常
     */
    public static void test2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        // fales 不抛出异常！
//        System.out.println(blockingQueue.offer("d"));

        System.out.println("===========");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // null
//        System.out.println(blockingQueue.poll());


    }

    /**
     * 等待，阻塞（一直阻塞）
     * @throws InterruptedException
     */
    public static void test3() throws InterruptedException {

        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        // 队列没有位置了，一直阻塞
//        blockingQueue.put("d");

        System.out.println("===========");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        // 没有这个元素，一直阻塞
//        System.out.println(blockingQueue.take());

    }

    /**
     * 等待，阻塞（等待超时）
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        // 等待超过2秒就退出
//        blockingQueue.offer("d", 2, TimeUnit.SECONDS);

        System.out.println("===========");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

//        等待超过2秒就退出
        blockingQueue.poll(2, TimeUnit.SECONDS);
    }
}