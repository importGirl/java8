package com.wdg.Collector;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangdg
 * @Description: Collector类
 * @date 2017-06-11 00:50:22
 */
public class CollectorDemo {

    private List<Member> members = null;

    @Before
    public void test1(){
         members = Arrays.asList(new Member("m1", "red", 15, 20L),
                new Member("m2", "green", 15, 20L),
                new Member("m3", "blue", 15, 20L),
                new Member("m4", "pink", 15, 20L),
                new Member("m5", "white", 15, 20L));
    }

    /**
     * groupingBy(): 根据某个值进行分组; 该方法返回的是一个Map,不能使用foreach()迭代方法
     */
    @Test
    public void testGroupingBy(){
        Optional.ofNullable(members.stream().collect(Collectors.groupingBy(Member::getColor))).ifPresent(System.out::println);

    }
}
