package com.aaa.javatest.thread_test;

public class ThreadLocal_test {
    public static void main(String args[]) {

        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
        integerThreadLocal.set(1);

        new Thread(() -> {
            integerThreadLocal.set(2);
            System.out.println("Thread ID:" + Thread.currentThread().getId() + ",integerThreadLocal = " + integerThreadLocal.get());
        }).start();

        new Thread(() -> {
            //do nothing
            System.out.println("Thread ID:" + Thread.currentThread().getId() + ",integerThreadLocal = " + integerThreadLocal.get());
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread ID:" + Thread.currentThread().getId() + ",integerThreadLocal = " + integerThreadLocal.get());
    }
    /*
Thread ID:11,integerThreadLocal = 2
Thread ID:12,integerThreadLocal = null
Thread ID:1,integerThreadLocal = 1
    * */
}
