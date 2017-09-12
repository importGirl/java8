package com.wdg.Time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class LocalDateTimeDemo {
    /**
     * now():   获取当前时间的LocalDateTime实例
     * of():
     * atTime():   LocalDate -> LocalDateTime
     */
    @Test
    public void test1(){
        System.out.println(LocalDateTime.now());// 2017-09-11T22:56:38.665
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.SEPTEMBER, 11, 22, 56, 50);
        System.out.println(dateTime);// 2017-09-11T22:56:50
        LocalDate date = LocalDate.parse("2017-09-01");
        LocalTime time = LocalTime.of(22, 58);
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        System.out.println(dateTime1);// 2017-09-01T22:58
        LocalDateTime dateTime2 = date.atTime(20, 33);
        System.out.println(dateTime2);// 2017-09-01T20:33
    }
}
