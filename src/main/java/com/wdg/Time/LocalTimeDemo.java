package com.wdg.Time;

import org.junit.Test;

import java.time.LocalTime;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class LocalTimeDemo {

    /**
     * of() :            获取指定时间的实例
     */
    @Test
    public void test1(){
        System.out.println(LocalTime.of(20, 30));// 20:30
        LocalTime time = LocalTime.of(20, 30);
        System.out.println(time.getHour());// 20
        System.out.println(time.getMinute());// 30
        System.out.println(time.withHour(2));// 02:30

// 20:30:20
        System.out.println(time.withSecond(20));
    }
}
