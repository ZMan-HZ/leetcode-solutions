package com.zzm.solutions.present;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 题目：多线程(某里云)
 * <p>
 * 实现两个线程使之交替打印1 -100：
 * 如：两个线程分别为Printer1 和Printer2，
 * 最后输出的结果：
 * Printer1 - 1；
 * Printer2 - 2；
 * Printer1 - 3；
 * Printer2 - 4；
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/26 星期一
 */
public class ThreadPrinter {

    private static AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (counter.get() < 100) {
                    synchronized (this) {
                        try {
                            this.notifyAll();
                            System.out.println(Thread.currentThread().getName() + "\t->\t" + counter.getAndIncrement());
                            this.wait();
                            System.out.println(Thread.currentThread().getName() + " after wait");
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                        }
                    }
                }
            }
        };
        Thread printer1 = new Thread(runnable, "Printer1");
        Thread printer2 = new Thread(runnable, "Printer2");
        printer1.start();
        Thread.sleep(10);
        printer2.start();
    }

}
