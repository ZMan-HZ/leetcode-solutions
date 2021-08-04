package com.zzm.solutions.present;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/28 星期三
 */
public class JVMDemos {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("请求cpu死循环");
        Thread.currentThread().setName("loop-thread-cpu");
        int num = 0;
//        ThreadPrinter.run();
        new Thread(new Runner()).start();
        while (true) {
            num++;
            if (num == Integer.MAX_VALUE) {
                System.out.println("reset");
                num = 0;
            }
        }


    }

    static class Runner implements Runnable {

        @Override
        public void run() {
            for (int index = 0; index < 1000; index++) {
                System.out.println("模拟内存泄漏");
                ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
                localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
            }
        }
    }

}
