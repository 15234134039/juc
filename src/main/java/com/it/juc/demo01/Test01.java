package com.it.juc.demo01;

public class Test01 {
    public static void main(String[] args) {
        new Thread().start();
        System.out.println(Runtime.getRuntime().availableProcessors());



    }
}
