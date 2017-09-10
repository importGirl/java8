package com.wdg.optional;

import org.junit.Test;

import java.util.*;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class OptionalDemo1 {

    /**
     * of():         创建一个Optional,如果传入的参数为null,则抛出nullPointException.
     * ofNullable(): 作用和of(),类似,但是它可以接受一个null参数,而不抛出异常,并返回一个空的optional
     * isPresent(): 如果值存在则返回true,为null则返回false
     * get()      : 如果Optional有值,则将其返回.没值则抛出NoSuchElementException
     * ifPresent(): 如果Optional有值则调用Cunsumer的方法,否则不做处理; 如果值为空字符串也是当做有值情况处理
     * orElse()   : 如果有值则将其返回,否则返回其指定的值(类似三元运算符)
     * orElseGet(): 作用与orElse()类似,但可以接受一个lambda表达式生成指定的值
     * map()      : 如果有值则返回map()参数Function转化的值,如果为null则返回null的optional
     * flatMap()  : 作用与map类似,但是flatMap()的lambda参数中,需要手动封装结果为Optional<U>(U为方法参数类型),而map是自动封装
     * filter()   :
     */
    @Test
    public void test1(){
        List<String> list = null;
        Map<String, Object> map = new HashMap<>();
        List<String> list2 = Arrays.asList("a", "b", "c", "d");
        System.out.println(Optional.ofNullable(map.get("name")).isPresent());
//        System.out.println(Optional.ofNullable(list).get());
        System.out.println("====================");
        Optional.ofNullable(list).ifPresent((System.out::println));
        System.out.println("====================");
        Optional.ofNullable(list2).ifPresent(System.out::println);
        System.out.println("==================================orElse()========================");
        System.out.println(Optional.ofNullable(list).orElse(list2));
        System.out.println(Optional.ofNullable(list2).orElse(list));
        System.out.println("==================================orElseGet()========================");
        System.out.println(Optional.ofNullable(list).orElseGet(()->Arrays.asList("1","2")));
        System.out.println("==================================orElseThrow()========================");
//        System.out.println(Optional.ofNullable(list).orElseThrow(RuntimeException::new));
//        System.out.println(Optional.ofNullable(list).orElseThrow(()->new RuntimeException("list的值为空")));
        System.out.println("==================================map()========================");
//        System.out.println(Optional.ofNullable(list).map(a -> a.size()).get());
//        System.out.println(Optional.ofNullable(list2).map(a -> a.size()).get());
        System.out.println("==================================flatMap()========================");
//        System.out.println(Optional.ofNullable(list2).flatMap(a->Optional.of(a.size())).get());
//        System.out.println(Optional.ofNullable(list).flatMap(a->Optional.of(a.size())).get());
        System.out.println("==================================filter()========================");
        System.out.println(Optional.ofNullable(list2).filter(a -> a.size() > 2));
        System.out.println(Optional.ofNullable(list).filter(a -> a.size() > 2));
    }


}
