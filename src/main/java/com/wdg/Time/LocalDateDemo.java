package com.wdg.Time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.*;

/**
 * @author wangdg
 * @Description: LocalDate类
 * @date 2017-06-11 00:50:22
 */
public class LocalDateDemo {

    /**
     * now(): 创建当前时间的实例
     * of():  指定特定的日期,创建LocalDate实例
     * parse():           解析指定日期,创建LocalDate实例
     * getDayOfYear():    获得当天在当年的第几天
     * getDayOfMonth():   获得当天在当月的第几天
     * getDayOfWeek():    获得当天在当周的第几天
     * isLeapYear():      是否闰年
     * withYear()  :      根据LocalDate天数来计算年月日
     * plusMonths():      月份加2
     * minusDays():       减去对应天数
     * with()     :       时间修改器
     */
    @Test
    public void test1(){
        System.out.println(LocalDate.now());// 2017-09-11
        System.out.println(LocalDate.of(2017, Month.JANUARY, 11));// 2017-09-11
        System.out.println(LocalDate.parse("2017-09-11"));
        LocalDate today = LocalDate.parse("2017-09-11");
        System.out.println(today.getDayOfYear());// 254
        System.out.println(today.getDayOfMonth());// 11
        System.out.println(today.getDayOfWeek());// MONDAY
        System.out.println(today.isLeapYear());//是否闰年
        System.out.println(today.withYear(2016));//2016-09-11
        System.out.println(today.plusMonths(2));// 2017-11-11
        System.out.println(today.minusDays(10));// 2017-09-01
        // 时间修改器  TemporalAdjuster
        LocalDate newDay = today.with(new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                return temporal.minus(1, ChronoUnit.DAYS);//把当前时间减去一天
            }
        });
        System.out.println(newDay);// 2017-09-10


    }
}
