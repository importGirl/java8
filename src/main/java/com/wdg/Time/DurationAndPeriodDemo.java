package com.wdg.Time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class DurationAndPeriodDemo {

    /**
     * Duration: 表示以秒和纳秒为基准的时长。例如，“23.6秒”
     * Period  : 表示以年、月、日衡量的时长。例如，“3年2个月零6天”
     */
    @Test
    public void test1(){
        Period sixMothod = Period.ofMonths(6);
        System.out.println(LocalDate.now().minus(sixMothod));// 2017-03-11

    }
}
