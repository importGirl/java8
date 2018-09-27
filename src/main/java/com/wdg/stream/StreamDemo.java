package com.wdg.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        System.out.println("==========================================================================");

        /**
         * 方法:  sort()
         * 作用:  排序
         * 分析:
         */

        /**
         * 方法: max()/min()/distinct()
         * 作用: 最大值/最小值/去重
         * 分析:
         */

        /**
         * 方法: allMatch()/anyMatch()/noneMatch()
         * 作用: 匹配规则: 全部匹配符合/任意匹配符合/全部匹配不符合
         * 分析:
         */
        List<Integer> matchList = Arrays.asList(1, 2, 8, 3, 4, 5, 6, 7);
        boolean isAllMatch = matchList.stream().allMatch(new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num > 3;
            }
        });
        System.out.println("AllMatch: " + isAllMatch);

        boolean isAnyMatch = matchList.stream().anyMatch(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 7;
            }
        });
        System.out.println("AnyMatch: " + isAnyMatch);
        System.out.println("==========================================================================");

        /**
         * 方法:  Stream.generate()
         * 作用:  自己生成流
         * 分析:
         */
        // 生成10个随机数
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return (int)System.nanoTime()%100;
            }
        }).limit(10).forEach(System.out::println);
        System.out.println("==========================================================================");

        /**
         * 方法: 自己实现Supplier
         * 作用: 生成海量测试数据
         * 分析:
         */
        Stream.generate(new PersonSupplier()).limit(10).forEach(p->System.out.println(p.getName()+","+p.getId()));
        System.out.println("==========================================================================");

        /**
         * 方法: groupingBy/ partitioningBy
         * 作用: 进行分组
         * 分析:
         */
        Map<Long, List<Person>> personGroups = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy(Person::getId));
        Iterator<Map.Entry<Long, List<Person>>> it = personGroups.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Long, List<Person>> p = it.next();
            System.out.println("key: " + p.getKey()+ " value: " + p.getValue().size());
        }

    }

    private static class PersonSupplier implements Supplier<Person>{
        private long index = 0;
        private Random random = new Random();

        @Override
        public Person get() {
            return new Person(index++,"stormTestUser"+index);
        }
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

        public Long getId() {
            return id;
        }

        public static void setId(Long id) {
            Person.id = id;
        }

        public static void setName(String name) {
            Person.name = name;
        }
    }
    public static void print(String text){
        Optional.ofNullable(text).ifPresent(s->System.out.print("当前str:"+s));
    }

    public static int getLength(String text){
        return Optional.ofNullable(text).map(String::length).orElse(-1);// 三元运算符 ?:
    }

}
