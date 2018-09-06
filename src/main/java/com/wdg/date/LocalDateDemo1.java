package com.wdg.date;

import org.junit.Test;

import java.util.Date;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class LocalDateDemo1 {
    @Test
    public void test1(){
        System.out.println(new Date());
        System.out.println(new Date().toInstant());
    }
}
