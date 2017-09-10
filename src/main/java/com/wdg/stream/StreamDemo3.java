package com.wdg.stream;

import org.junit.Test;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description: 无限Stream: generate(),limit(),foreach(),iterate()
 * @date 2017-06-11 00:50:22
 */
public class StreamDemo3 {

    @Test
    public void test1(){
        Stream.generate(()->"1")//生成无限流
        .limit(10)// 取流的10个元素
        .forEach(System.out::println);
    }

    /**
     *  1. 创建100个对象
     *  2. 创建1000个随机数
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Stream.generate(User::new).limit(100).forEach(System.out::println);
        Stream.generate(Math::random).limit(1000).forEach(System.out::println);
    }

    /**
     * 产生规律的数据
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Stream.iterate(1,x->x+1).limit(100).forEach(System.out::println);
        Stream.iterate(1, UnaryOperator.identity()).limit(100).forEach(System.out::println);// 对种子1不进行变化
    }
}
