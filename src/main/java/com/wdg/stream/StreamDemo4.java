package com.wdg.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description: Stream常用方法
 * @date 2017-06-11 00:50:22
 */
public class StreamDemo4 {

    private School school = new School();

    @Before
    public void test1(){
        Class c = new Class(1);
        Stream.iterate(c,cls->new Class(cls.getClassNo()+1)).limit(20).forEach(x->school.getClasses().add(x));// 生成20个班级,加入到学校的班级集合中
        System.out.println("init完成: ");

    }

    /**
     *  1.筛选出以偶数开头的班级编号
     *  2.刷选出班级人数大于20的班级
     */
    @Test
    public void test2(){
//        school.getClasses().stream().filter(c->c.getClassNo()%2==0).forEach(System.out::println);
        school.getClasses().stream().filter(c->c.getStudents().size()>=20).forEach(System.out::println);
    }

    /**
     *  3. 列出80中班级编号是10的班级的学生的最高分
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Stream.of(school).filter(s->s.getName().equals("广州市第80中学"))// 1.对学校流 => Stream<School>
                         .flatMap(s->s.getClasses().stream())// 2.得到班级流 => Stream<Class>
                         .filter(s->s.getClassNo()==10)
                         .flatMap(c->c.getStudents().stream())// 3.得到学生流 => Stream<Student>
                         .map(s->s.getScore())// 4.得到班级10的学生的分数流
                         .max(Comparator.comparing(Function.identity()))// 5.根据分数进行排序
                         .ifPresent(System.out::println);
    }

    /**
     * flatMap: 合并流   Stream<String[]> => Stream<String>
     *          传入A类型的流,返回B类型的流
     */
    @Test
    public void test4() throws Exception {
        String[] s1 = {"a", "b", "c", "c"};
        String[] s2 = {"a", "b", "c", "c"};
        String[] s3 = {"a", "b", "c", "c"};
        Stream.of(s1,s2,s3).flatMap(arr-> Arrays.stream(arr)).forEach(System.out::println);
    }

    /**
     * concat: 合并两个同类型的流
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        List<String> list = Arrays.asList("b", "c", "d");
        List<String> list2 = Arrays.asList("e", "f", "a");
        Stream.concat(list.stream(),list2.stream()).forEach(System.out::println);
    }

    public void print(Integer num){
        System.out.println(Thread.currentThread().getName()+":"+num);
    }

    /**
     *  parallelStream: 把流转变为并行流
     *  peek:           监控管道中的流
     *  sorted:         无参 -> 流的数据类型必须实现了Compatable接口,默认安装自然排序
     *                  有参 ->
     *  forEachOrder:   顺序输出,使用并行计算是,即使排序了也是不会顺序输出的,forEachOrder保证能顺序输出
      * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1, 0,null);
        List<String> list3 = Arrays.asList("b", "c", "d");
        List<String> list2 = Arrays.asList("e", "f", "a");
//        list.parallelStream().peek(this::print).filter(x->x>1).sorted(Comparator.comparing(a->a)).forEach(System.out::println);
//        list.stream().sorted().forEach(System.out::println);
//        Stream.of(list2,list3).sorted().forEach(System.out::println);//报错
//        list.parallelStream().sorted().forEachOrdered(System.out::println);
       list.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).forEach(System.out::println);// 把null放在第一位

    }



}

























