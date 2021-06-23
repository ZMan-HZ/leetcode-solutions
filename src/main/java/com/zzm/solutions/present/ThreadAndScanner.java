package com.zzm.solutions.present;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 某宝
 * <p>
 * 1、编写一个小程序，从键盘读取用户输入的两个字符串，若输入是字符串，实现这两个字符串的拼接、若输入整数和浮点数则 两个输入相加。
 * 要进行异常处理，对输入的不符合要求的字符串（包涵特殊字符的字符串）提示给用户，不能使程序崩溃。
 * 2、编写一个小程序，启X个线程，完成大批量资金入账（金额累加到这个账户里）
 * 在执行时，线程数可以设置为5个，流入资金笔数可以先设定为10笔，账户初始资金为0
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/23 星期三
 */
public class ThreadAndScanner {

    private AtomicReference<BigDecimal> reference = new AtomicReference<>(new BigDecimal("0.00"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String str2 = scanner.nextLine();
        try {
            String s = merge(str, str2);
            System.out.println(s);
        } catch (IllegalArgumentException e) {
            String msg = String.format("Illegal argument: %s\t%s", str, str2);
            System.out.println(e);
            System.out.println(msg);
        }

        List<BigDecimal> bankMoney = new ArrayList<BigDecimal>() {{
            add(new BigDecimal("1.1"));
            add(new BigDecimal("13"));
            add(new BigDecimal("11.3"));
            add(new BigDecimal("1.4"));
            add(new BigDecimal("2.5"));
            add(new BigDecimal("5.6"));
            add(new BigDecimal("1.5"));
            add(new BigDecimal("1.7"));
            add(new BigDecimal("1.9"));
            add(new BigDecimal("1.0"));
        }};

        ThreadAndScanner demo = new ThreadAndScanner();
        BigDecimal bigDecimal = demo.accountIn(bankMoney, 3);
        System.out.println("Account:" + bigDecimal);

    }

    //未考虑数字和字符串混合的情况，\W
    public static String merge(String str1, String str2) {
        String str = "";

        if (isAlpha(str1) && isAlpha(str2)) {
            str = str1 + str2;
        } else if (isDigit(str1) && isDigit(str2)) {
            //保留小数位与最多的一个一致
            int scale = str1.length() - str1.indexOf(".") - 1;
            scale = Math.max(scale, str2.length() - str2.indexOf(".") - 1);
            if (!str1.contains(".") && !str2.contains(".")) {
                scale = 0;
            }
            BigDecimal d1 = new BigDecimal(str1);
            d1 = d1.setScale(scale, RoundingMode.HALF_UP);
            BigDecimal d2 = new BigDecimal(str2);
            d2 = d2.setScale(scale, RoundingMode.HALF_UP);
            BigDecimal d = d1.add(d2);
            str = d.toString();
        } else {
            throw new IllegalArgumentException();
        }

        return str;
    }


    private static boolean isAlpha(String str) {
        String alpha = "^[A-Za-z]+$";
        Pattern pattern1 = Pattern.compile(alpha);
        Matcher matcher1 = pattern1.matcher(str);
        return matcher1.matches();
    }

    private static boolean isDigit(String str) {
        String num = "[+-]?\\d+\\.?\\d*";
        Pattern pattern = Pattern.compile(num);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private BigDecimal accountIn(List<BigDecimal> bankAccounts, Integer corePoolSize) {
        if (Objects.isNull(bankAccounts) || bankAccounts.isEmpty()) {
            return reference.get();
        }
        CountDownLatch latch = new CountDownLatch(bankAccounts.size());
        corePoolSize = Math.min(5, Math.max(1, corePoolSize));

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, 5, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (BigDecimal money : bankAccounts) {
                Calculator calculator = new Calculator(money, latch);
                executor.execute(calculator);
            }
            latch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return reference.get();
    }

    private class Calculator implements Runnable {

        private BigDecimal money;
        private CountDownLatch latch;

        Calculator(BigDecimal money, CountDownLatch latch) {
            this.money = money;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                reference.updateAndGet(s -> s.add(money));
            } finally {
                latch.countDown();
            }
        }
    }

}
