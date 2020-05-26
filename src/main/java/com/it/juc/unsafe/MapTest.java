package com.it.juc.unsafe;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 直接用hashmap会抛出ConcurrentModificationException
 */
public class MapTest {
    public static void main(String[] args) {

        // 默认等价于什么？  new HashMap<>(16,0.75);
        // Map<String, String> map = new HashMap<>();
        // 研究ConcurrentHashMap的原理
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
