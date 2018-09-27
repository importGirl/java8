package com.wdg.share.stream;


import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    /**
     * 学生集合
     */
    public static List<Student> students;
    static {
        Student s1 = new Student("张三", 20, 1, "小北大学");
        Student s2 = new Student("李四", 30, 1, "小北大学");
        Student s3 = new Student("王三", 10, 1, "小北大学");

        Student s4 = new Student("王宝强", 30, 2, "北京大学");
        Student s5 = new Student("周星驰", 10, 2, "北京大学");
        Student s6 = new Student("杨幂", 18, 2, "北京大学");

        students = Arrays.asList(s1, s2, s3, s4, s5, s6);
    }

    @Test
    public void testBoxStream() {
        ArrayList strArr = new ArrayList();
        strArr.add("1223");
        strArr.add("323423");
        strArr.stream().forEach(System.out::println);
        IntStream.of(1, 2, 3, 4, 2).sorted();
        IntStream.range(1, 100).forEach(i -> System.out.println(i));
    }

    /**
     * 包装类型Stream: IntStream, DoubleStream, longStream
     * range(): 返回子序列 [a,b)，左闭右开，意味着不包括 b
     * rangeClose(): 返回子序列 [a,b]，左闭又闭。意味着包括 b 元素，增长步值为 1
     */
    @Test
    public void testIntStream() {

        IntStream.range(1, 100).forEach(System.out::println);        //[1,99)
        IntStream.rangeClosed(1, 100).forEach(System.out::println);  //[1,100]
        System.out.println(IntStream.rangeClosed(1, 100).sum());    //5050
        IntStream.of(199, 22, 4, 312, 6).sorted().forEach(System.out::println);  //排序
        System.out.println(IntStream.of(199, 22, 4, 312, 6).average());
        System.out.println(IntStream.of(199, 22, 4, 312, 6).max());
    }

    /**
     * Stream转换为其它数据结构
     * 1.Array:  toArray()
     * <p>
     * 2.Collection: Collectors.toList()
     * <p>
     * 3.String: toString()
     */
    @Test
    public void testStreamTransform() {
        List<String> strList = Arrays.asList("张三", "李四", "王五");
        String[] strArray = strList.stream().toArray(s -> new String[s]);
        List<String> strList2 = strList.stream().map(String::toString).collect(Collectors.toList());
        String strJoin = strList.stream().collect(Collectors.joining(",")).toString();
        System.out.println(Arrays.toString(strArray));
//        System.out.println(strList2);
        System.out.println(strJoin);

    }


    /**
     * -----------------------------------------------中间流操作------------------------------------------------------------------
     * map: 对Stream中的元素进行转换操作
     * mapToInt: 对Stream中的元素转换成Int类型
     * flatMap:  把子Stream中的元素压缩到父集合中；
     * filter:      使用给定函数过滤Stream中的元素;
     * distinct:    对Stream中的元素进行去重操作;
     * sorted:      对Stream中的元素进行排序
     * peek:        对每个元素执行操作并返回一个新的 Stream  *****可以查看流的转换过程,和终止函数******
     * limit:       返回 Stream 的前面 n 个元素
     * skip:        扔掉前 n 个元素
     * ***********优化: 首先对 Stream 进行各类 map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，这能帮助程序明显缩短执行时间
     * parallel:    返回一个并行流
     * sequential:  返回一个串行流
     * unordered:   顺序流转为无序流(如何使用?)
     */
    @Test
    public void testIntermediate() {
        // map: 获得所有学生的姓名
        System.out.println(students.stream().map(student -> student.getName()).collect(Collectors.toList()).toString());
        // mapToInt: 获得所有学生的年龄
        int[] ages = students.stream().mapToInt(student -> student.getAge()).toArray();
        System.out.println(Arrays.toString(ages));
        // flatMap: 把多个集合的元素放到一个集合中
        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2), Arrays.asList(2, 4, 2), Arrays.asList(12, 24));
        System.out.println(listStream.flatMap(nums -> nums.stream()).collect(Collectors.toList()).toString());

        System.out.println("======================================================================================");


        // filter: 过滤出年龄小于等于20的学生
        System.out.println(Arrays.toString(students.stream().filter(student -> student.getAge() <= 20).toArray()));
        // distinct: 根据学校去重
        System.out.println(Arrays.toString(students.stream().map(Student::getName).distinct().toArray()));
        // sorted:  根据年龄对学生进行排序
        System.out.println(Arrays.toString(students.stream().sorted(Comparator.comparing(Student::getAge)).toArray()));

        System.out.println("======================================================================================");


        // peek:    筛选北京大学,打印学生,转换成学生的姓名
        students.stream().filter(student -> "北京大学".equals(student.getSchool())).peek(System.out::println).map(Student::getName).peek(System.out::println).toArray();

        System.out.println("======================================================================================");

        // limit:   返回前3个学生,跳过第一个学生
        students.stream().limit(3).skip(1).peek(System.out::println).toArray();


    }

    /**
     * -----------------------------------------------终止流操作------------------------------------------------------------------
     * 终止流操作:  Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次操作
     * forEach:         循环Stream中元素(并行)
     * forEachOrdered:  循环Stream中元素(串行/顺序)
     * toArray:         把Stream元素转换为数组
     * reduce:          主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
     *                  从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
     * min、 max:       最小,最大值
     * sum:             求和
     * count:           求Stream元素总数
     * anyMatch:        Stream 中只要有一个元素符合传入的 predicate，返回 true
     * allMatch:        Stream 中全部元素符合传入的 predicate，返回 true
     * noneMatch:       Stream 中没有一个元素符合传入的 predicate，返回 true
     * findFirst:       返回第一个元素的Optional
     * findAny:         查找任何一个就返回 Optional
     * iterate:         根据给定的seed,生成一个无限的流,通常跟limit()一起使用，限制流中元素的个数
     * generate:        根据任何计算方式来生成,生成一个无限的流,通常跟limit()一起使用，限制流中元素的个数
     * collect:         Stream转换成容器的方法,元素收集
     * concat:          合并两个Stream
     */
    @Test
    public void testTerminal() {
        // forEach/forEachOrdered:  打印学生信息
        students.stream().parallel().forEach(System.out::println);
        System.out.println("======================================================================================");
        students.stream().parallel().forEachOrdered(System.out::println);

        // toArray: 生成指定长度的数组
        System.out.println(Arrays.toString(students.stream().toArray(n -> new Student[n])));
        System.out.println("======================================================================================");
        // reduce:求和/求最小值/字符串拼接
        System.out.println(students.stream().map(Student::getName).reduce("", String::concat).toString());
        System.out.println(students.stream().map(Student::getAge).reduce(0, Integer::sum));
        System.out.println(students.stream().map(Student::getAge).reduce(Integer::sum).get());
        System.out.println(students.stream().map(Student::getAge).reduce(Integer.MAX_VALUE, Integer::min));
        System.out.println(students.stream().mapToInt(Student::getAge).max());
        System.out.println(students.stream().map(Student::getAge).parallel().reduce(3, Integer::sum, Integer::sum));// 136 并行流才起作用
        System.out.println("======================================================================================");

        // anyMatch、 allMatch、 noneMatch
        System.out.println(students.stream().allMatch(student -> student.getSchool().equals("小北大学")));
        System.out.println(students.stream().anyMatch(student -> student.getSchool().equals("小北大学")));
        System.out.println(students.stream().noneMatch(student -> student.getSchool().equals("小贝大学")));

        System.out.println("======================================================================================");

        // findAny: 使用并行流才会有效果
        System.out.println(students.stream().parallel().peek(System.out::println).findAny().get());

        System.out.println("======================================================================================");
        // iterate: 和reduce类似
        Stream.iterate(0, n->n+3).limit(10).forEach(System.out::println);
        System.out.println("-------------");
        // generate: 打印斐波那契数列
        Stream.generate(new Supplier<Long>() {
            long a = 0,b=1;
            @Override
            public Long get() {
                long tmp = a + b;
                a = b;
                b = tmp;
                return a;
            }
        }).limit(10).forEach(System.out::println);
        System.out.println("======================================================================================");
        IntStream.of(12, 11, 123, 1, 3).unordered().forEach(System.out::println);


    }


    /**
     * -----------------------------------------------Collector操作------------------------------------------------------------------
     * Stream转换成容器: Collectors.toList()/toSet()/toMap()/toCollection()
     * groupBy:                     集合元素进行分组
     * joining:                     连接集合元素
     * averagingDouble,averagingInt,averagingLong:             求集合的平均值
     * collectingAndThen:           进行一个Collector操作后再进行另外一个操作
     * counting:                    统计元素个数
     * summarizingDouble:           count, sum, min, average, max 同时求
     * mapping:                     和Stream的map类似
     * partitioningBy:              对元素进行二分区操作(分区)
     * reducing:
     */
    @Test
    public void testCollectors() {
        // toCollection(): 转换成自定义容器
        ArrayList arrayList = students.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(students.stream().collect(Collectors.toMap(Student::getName, Student::getAge)));
        System.out.println(students.stream().collect(Collectors.toMap(Student::getSchool, Student::getName, (s, n) -> s + n, ConcurrentHashMap::new)).toString());
        // (key,value,重复key使用哪个key,返回的集合类型)
//                .toString());
        // groupBy: 按学校分组
        System.out.println(students.stream().collect(Collectors.groupingBy(student -> student.getSchool())));
        System.out.println(students.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.groupingBy(Student::getClas))));// 多级分组
        System.out.println(students.stream().collect(Collectors.groupingBy(Student::getSchool, HashMap::new, Collectors.groupingBy(Student::getClas))).toString());
        // joining: 连接元素
        System.out.println(students.stream().map(student -> student.getName()).collect(Collectors.joining(",", "[", "]")));
        // averagingDouble,collectingAndThen: 求平均值然后+1
        System.out.println(students.stream().collect(Collectors.collectingAndThen(Collectors.averagingDouble(Student::getAge), age -> age + 1)).toString());
        // counting: 统计学生个数
        System.out.println(students.stream().collect(Collectors.counting()));
        // summarizingDouble: count, sum, min, average, max
        System.out.println(students.stream().collect(Collectors.summarizingInt(student -> student.getAge())));
        System.out.println(students.stream().collect(Collectors.summingInt(Student::getAge)));
        // mapping:  和Stream的map类似
        System.out.println(students.stream().collect(Collectors.mapping(Student::getName, Collectors.joining(","))));
        // unmodifiableCollection : 使集合不能被修改
        System.out.println(Collections.unmodifiableCollection(students).add(new Student()));
        // partitioningBy: 对元素进行二分区操作时用到。 大于18和小于是18
        System.out.println(students.stream().collect(Collectors.partitioningBy(student -> student.getAge()>=18)).toString());
        System.out.println(students.stream().collect(Collectors.partitioningBy(student -> student.getAge() >= 18, Collectors.toList())));
        // reducing: 自定义reduce
        System.out.println(students.stream().collect(Collectors.reducing((s1, s2) -> s1.getAge() > s2.getAge() ? s1 : s2)).toString());
        System.out.println(students.stream().collect(Collectors.reducing(0,Student::getAge,(s1, s2) -> s1+s2))
                .toString());

    }



    public static void main(String[] args) {
        IntStream.range(5, 3).forEach(i -> System.out.println(i));

    }
}
























