package com.wdg.methodReferences;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */

public class MethodReferenceDemo {

    /**
     * =============================================方法引用==========================================
     * 方法引用需要满足的条件:
     *  1. 接口方法中的代码块中只有一行代码
     *  2. 接口方法中的代马块中的方法的参数列表和返回值 与 接口的方法的参数列表/返回值是相同的
     *
     */
    // 对象::方法
    @Test
    public void testObjectMethod()throws Exception{
        List<String> list = Arrays.asList("d", "ddd", "12");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        // lambda表达式 (只有一行代码)
        list.forEach(s->System.out.println(s));
        // 方法引用 println() 的参数列表和返回值与 accept()的参数列表和返回值相同,所以可以使用方法引用
        list.forEach(System. out::println);

    }

    // 类::静态方法
    @Test
    public void testClassStaticMethod() throws Exception {
        Integer[] arr = new Integer[]{1, 2, 1, 6, 2};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        // lambda表达式
        Arrays.sort(arr,(o1,o2)->Integer.compare(o1,o2));
        // 类::静态方法   Integer的compare参数列表作为Comparator的compare方法的参数列表,Integer的compare的返回值可以作为Comparator的compare方法的返回值
        Arrays.sort(arr,Integer::compare);
    }


    /** 类::实例方法
     *  1. 接口方法中的代码块中只有一行代码
     *  2. 接口方法中的代马块中的方法的参数列表和返回值 与 接口的方法的参数列表/返回值是相同的
     *  3. 若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时
     */

    @Test
    public void testClassInstanceMethod() {
        List<Long> longs = Arrays.asList(1L,2L,2L);
        List<Object> list = Arrays.asList(1,5,2,3);
        List<String> strings = Arrays.asList("5","2","2");
        longs.forEach(l->l.toString());
        longs.forEach(Long::intValue);
        list.forEach(l->l.toString());
        list.forEach(Object::toString);
        // s为lambda的参数列表的第一个参数，是实例方法toString()的调用者，没有第二个参数，toString()方法也没有参数
        strings.forEach(s->s.toString());
        strings.forEach(String::toString);


    }

    
    /**
     * =================================构造方法引用==============================================
     * 总结: 需要满足的要求:
     * 1. 默认需要无参的构造方法
     * 2. 参数列表和方法返回值相同
     *
     */

    public <T> void launch(IComputer<T> computer){
        computer.showWord();
    }
    // 类::new
    @Test
    public void testConstructerMethod() throws Exception {
        this.launch(new IComputer<String>() {
            @Override
            public String showWord() {
                return new String();
            }
        });
        // lambda表达式
        this.launch(()->new String());
        // 类::new
        this.launch(String::new);// 把new String()的结果作为showWord的返回值返回
    }

    /**
     * =================================数组引用==============================================
     *
     */
    @Test
    public void testArraysMethod() {
        // 匿名函数
        Function<Integer,int[]> function = new Function() {
            @Override
            public Object apply(Object i) {
                return new int[8];
            }
        };

        // lambda表达式
        Function<Integer,int[]> function2 = i->new int[i];

        // 数组引用
        Function<Integer,int[]> function3 = int[]::new;
    }
}
