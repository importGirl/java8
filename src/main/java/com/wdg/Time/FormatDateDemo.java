package com.wdg.Time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wangdg
 * @Description: LocalDateTime => 字符串 => LocalDateTime
 * @date 2017-06-11 00:50:22
 */
public class FormatDateDemo {

    @Test
    public void test1(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("默认格式化: " + date); // 2017-09-11T23:41:42.156
        System.out.println("自定义格式化: " + date.format(f)); // 2017-09-11 11:41:42
        LocalDateTime parseDate = LocalDateTime.parse("2017-09-28 11:33:22", f);//字符串->时间
        System.out.println("字符串转LocalDateTime: " + parseDate);// 2017-09-28T11:33:22
        System.out.println("LocalDateTime => 字符串: " + parseDate.format(f));// 2017-09-28 11:33:22
    }

    /**
     * Date 和 Instant的互相转换
     */
    @Test
    public void test2(){
        // Date 和 Instant
        Date oldDate = Date.from(Instant.now());
        Instant instant = oldDate.toInstant();
        System.out.println(oldDate);// Mon Sep 11 23:47:15 CST 2017
        System.out.println(instant);// 2017-09-11T15:47:15.428Z
    }

    /**
     * Date => LocalDateTime
     * LocalDateTime => Date
     * 总结: LocalDateTime和Date的转化必须有中间类ZoneId
     */
    @Test
    public void test3(){
//        LocalDateTime date = LocalDateTime.from(new Date());
        LocalDateTime localDateTime = LocalDateTime.now();
        /**
         * 1.获得当前时间的毫秒数
         * 2.转化为ZoneDateTime  (ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则)
         * 3.ZoneDateTime转为Instant
         * 4.使用Instant -> Date 的API
         */
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);// Mon Sep 11 23:56:28 CST 2017
        Date date2 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date2);// Mon Sep 11 00:00:00 CST 2017
    }

    /**
     *      ZoneId:      时区ID，用来确定Instant和LocalDateTime互相转换的规则
     *      Instant:     用来表示时间线上的一个点
     *      LocalDate:   表示没有时区的日期, LocalDate是不可变并且线程安全的
     *      LocalTime:   表示没有时区的时间, LocalTime是不可变并且线程安全的
     *      LocalDateTime: 表示没有时区的日期时间, LocalDateTime是不可变并且线程安全的
     *      Clock:       用于访问当前时刻、日期、时间，用到时区
     *      Duration:    用秒和纳秒表示时间的数量
     *
     */
    @Test
    public void test4() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()));// 2017-09-11T23:54:02.755+08:00[Asia/Shanghai]
    }
}
