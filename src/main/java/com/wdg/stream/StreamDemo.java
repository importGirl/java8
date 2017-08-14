package com.wdg.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        /**
         * 方法: stream.map()
         * 作用: 1对1映射
         * 分析: flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起
         */
        List<Object> list2 = list.stream().map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer n) {
                return n * n;
            }
        }).collect(Collectors.toList());
        List<Object> list3 = list.stream().map( n -> n * n).collect(Collectors.toList());
        for (Object ele : list2) {
            System.out.println(ele);
        }

        /**
         * 方法: stream.flatMap()
         * 作用: 1对多映射
         * 分析: flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起
         */
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3));
        Stream<Integer> outputStream1= inputStream.flatMap(new Function<List<Integer>, Stream<? extends Integer>>() {
            @Override
            public Stream<? extends Integer> apply(List<Integer> integers) {
                return integers.stream();
            }
        });
//        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
        System.out.println("============================================================");
        /**
         * 方法: stream.filter()
         * 作用: 对stream的元素进行测试,通过的则保存下来
         * 分析: 符合条件的才能通过该过滤器,过滤掉不符合条件的
         */
        Integer[] nums = {1,2,3,4,5,6,7,8};
        Integer[] even = Stream.of(nums).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        }).toArray(Integer[]::new);
        System.out.println(Arrays.toString(even));
        System.out.println("==========================================================================");

        /**
         * 方法: stream.forEach()
         * 作用: 相当于foreach,stream中的元素取出一次就被消费掉了
         * 分析: 不能取第二次,peek能取第二次
         */
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        intList.stream().forEach(System.out::print);
        System.out.println("==========================================================================");

        /**
         * 方法: stream.peek()
         * 作用: 取出集合中的每一个元素
         * 分析: 可以重复取
         */
        List<String> strList = Arrays.asList("a", "b", "c", "d");
        List<String> strList2 = strList.stream().peek(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        }).peek(System.out::println)
          .map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(strList2);
        System.out.println("==========================================================================");

        /**
         * 方法: Optional.ofNullable(str).ifPresent(fun);
         * 作用: str!=null 则执行fun代码
         * 分析:
         */
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        System.out.println("==========================================================================");
        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));
        System.out.println("==========================================================================");

        /**
         * 方法: stream.reduce()
         * 作用: 把元素组合起来,提供一个起始值,按照一定的运算规则进行计算
         * 分析:
         */
        String strReduce = Stream.of("A", "B", "c", "D").reduce("", String::concat);
        System.out.println(strReduce);
        Optional<String> strReduce2 = Stream.of("a", "b", "c", "d").reduce(String::concat);
        System.out.println(strReduce2.get());// abcd
        System.out.println("==========================================================================");

        /**
         * 方法: stream.limit()/skip()
         * 作用: limit->返回前n个元素,skip->扔掉前n个元素
         * 分析:
         */
        List<Object> persons = new ArrayList<>();
        for (Integer i = 0; i <20 ; i++) {
            Person person = new Person(i.longValue(), "person" + i);
            persons.add(person);
        }
        List<Object> collect =(persons.stream().limit(10).skip(3).collect(Collectors.toList()));
    }

    static class Person{
        private static  Long id;
        private static  String name;

        public Person(Long id, String name) {
            this.id = id;
            this.name = name;
        }
        private static  String getName(){
            System.out.println(name);
            return name;
        }


    }
    public static void print(String text){
        Optional.ofNullable(text).ifPresent(s->System.out.print("当前str:"+s));
    }

    public static int getLength(String text){
        return Optional.ofNullable(text).map(String::length).orElse(-1);// 三元运算符 ?:
    }

}
