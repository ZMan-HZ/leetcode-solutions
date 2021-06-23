package com.zzm.solutions.present;


/**
 * 某滴
 * <p>
 * 写一个死锁
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/23 星期三
 */
public class DeadLock {


    /**
     * 简单实现
     */
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> runnable1());
        Thread thread2 = new Thread(() -> runnable2());
        thread1.start();
        thread2.start();
    }

    private static void runnable1() {
        synchronized (lock1) {
            System.out.println("A");
            synchronized (lock2) {
                System.out.println("B");
            }
        }
    }

    private static void runnable2() {
        synchronized (lock2) {
            System.out.println("C");
            synchronized (lock1) {
                System.out.println("D");
            }
        }
    }

}
