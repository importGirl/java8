package com.wdg.collector;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
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
                new Member("m1", "green", 15, 20L),
                new Member("m3", "blue", 15, 20L),
                new Member("m4", "pink", 15, 20L),
                new Member("m5", "white", 15, 20L));
    }

    /**
     * groupingBy(): 根据某个值进行分组; 该方法返回的是一个Map,不能使用foreach()迭代方法
     */
    @Test
    public void testGroupingBy(){
        Optional.ofNullable("根据会员颜色分组: "+members.stream().collect(Collectors.groupingBy(Member::getColor))).ifPresent(System.out::println);
        Optional.ofNullable("求会员分数的平均值: "+members.stream().collect(Collectors.averagingDouble(Member::getScore))).ifPresent(System.out::println);
        System.out.println("求年龄平均值后并进行加1: "+members.stream().collect(Collectors.collectingAndThen(Collectors.averagingDouble(m -> m.getAge()), avg -> avg+1)).toString());
        System.out.println("统计member的个数: "+members.stream().collect(Collectors.counting()).toString());
        System.out.println("根据名称分组并统计会员总数: "+members.stream().collect(Collectors.groupingBy(m -> m.getName(), Collectors.counting())).toString());
        System.out.println("分组->求平均值->返回一个treeMap: "+members.stream().collect(Collectors.groupingBy(m -> m.getName(), TreeMap::new, Collectors.averagingDouble(m -> m.getScore()))).toString());
        System.out.println("count: sum: min: average: max: "+members.stream().collect(Collectors.summarizingDouble(m -> m.getScore())).toString());
        System.out.println("根据名字分组,返回一个concurrentMap"+members.stream().collect(Collectors.groupingByConcurrent(m -> m.getName())).toString());
        System.out.println("连接集合的元素,逗号隔开: "+members.stream().map(member -> member.getName()).collect(Collectors.joining(",")).toString());
        System.out.println("连接集合元素: "+members.stream().map(m -> m.getName()).collect(Collectors.joining(",", "[", "]")).toString());
        System.out.println("mapping时,可以不使用stream的map: "+members.stream().collect(Collectors.mapping(m -> m.getName(), Collectors.joining(","))).toString());
        System.out.println("使集合不能被修改: "+Collections.unmodifiableCollection(members).add(new Member()));// UnsupportedOperationException
    }
}
