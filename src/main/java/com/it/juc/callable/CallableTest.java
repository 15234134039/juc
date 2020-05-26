package com.it.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  new Thread(new Runnable()).start();
 *  new Thread(new FutureTask<V>()).start();
 *  new Thread(new FutureTask<V>(Callable)).start();
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        // 适配类
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask, "A").start();

        // 结果会被缓存，效率高
        // TODO 缓存的原理是什么
        new Thread(futureTask, "B").start();

        // 这个get 方法可能会产生阻塞！把他放到最后 或者使用异步通信来处理！
        Integer o = (Integer) futureTask.get();
        System.out.println(o);
    }


}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 123;
    }
}
