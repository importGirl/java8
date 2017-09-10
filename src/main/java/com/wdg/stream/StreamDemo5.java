package com.wdg.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description: reduce , match
 * @date 2017-06-11 00:50:22
 */
public class StreamDemo5 {

    /**
     * reduce: 把Stream元素组合起来: 可以做求和,最小值,拼接字符串操作
     * ==== 总结: reduce提供一个种子,依照运算规则(BinaryOperator),和之前的第1,2,3,4,n个元素进行组合
     *
     * concat: 连接两个Stream元素,只能做元素的拼接操作
     */
    @Test
    public void test1(){
       // 1.拼接字符串
        System.out.println(Stream.of("a", "b", "c", "d").reduce("", (s, s2) -> s + s2));
        // 2.求最小值
        System.out.println(Stream.of(1, 2, 3, 4, 0, 2).reduce(Integer::min).get());
        System.out.println(Integer.MAX_VALUE);
        // 3.求和
        System.out.println(Stream.of(2, 5, 2, 5, 34).reduce(Integer::sum).get());
    }


    /**
     *  allMatch: 所有stream元素都匹配才返回true
     *  anyMatch: 任何一个Stream都匹配才会返回true
     *  nonMatch: 没有一个Stream匹配才返回true
     *
     */
    @Test
    public void test2(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 2);
        System.out.println(list.stream().allMatch(x -> x > 2));
        System.out.println(list.stream().anyMatch(x -> x == 2));
        System.out.println(list.stream().noneMatch(x -> x == 2));
    }

}
