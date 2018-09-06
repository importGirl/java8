package com.wdg.time;

import org.junit.Test;

import java.time.Instant;

/**
 * @author wangdg
 * @Description: Instant :毫秒数
 * @date 2017-06-11 00:50:22
 */
public class InstantDemo {

    /**
     *
     */
    @Test
    public void test1(){
        Instant start = Instant.now();
        Instant end = Instant.now();
        /**
         * 如果[boolean表达式]为true，则程序继续执行。
         * 如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]。
         */
        assert end.isAfter(start);

    }

}
