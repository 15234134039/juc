package com.it.juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁）一次只能被一个线程占有
 * 共享锁（读锁）多个线程可以同时占有
 * ReadWriteLock
 *
 * 这是什么意思？
 * 读-读可以共存！
 * 读-写不能共存！
 * 写-写不能共存！
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock cacheLock = new MyCacheLock();

        // 写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                cacheLock.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        // 读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                cacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }

    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 读写锁：更加细粒度的控制
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 存，写入的时候，只希望同时只有一个线程写
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    /**
     * 取，读，所有人都可以读！
     */
    public void get(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }


}
