package com.zzm.solutions.patterns.singleton;

import java.util.Objects;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class Singleton2 {

    private static Singleton2 instance;
    private Singleton2() {
    }


    public static Singleton2 getInstance(){
        if(Objects.isNull(instance)){
            instance = new Singleton2();
        }
        return instance;
    }

}
