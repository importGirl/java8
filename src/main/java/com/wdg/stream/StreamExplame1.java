package com.wdg.stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wangdg
 * @Description: 院长-Stream1例子
 * @date 2017-06-11 00:50:22
 */
public class StreamExplame1 {

    // 需求: 找出集合中分数大于85分的学生,然后进行降序排序,并打印每个学生的分数
    private List<User> list = new ArrayList<>();
    @Before
    public void addScore(){
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(new User(random.nextInt(50)+50));
        }
    }

    /** 传统的方式 */
    @Test
    public void testTrandition(){
        List<User> goods = new ArrayList<>();
        for (User u : list) {
            if(u.getScore()>85) goods.add(u);
        }

        goods.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o2.getScore(),o1.getScore());
            }
        });

        List<Integer> scores = new ArrayList<>();
        for (User user : goods) {
            scores.add(user.getScore());
        }
        System.out.println(scores);
    }

    /** Stream方式 */
//    @After
    public void testStream() throws Exception {
        List<Integer> scores = list.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getScore() > 85;
            }
        }).sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        }).map(new Function<User, Integer>() {
            @Override
            public Integer apply(User user) {
                return user.getScore();
            }
        }).collect(Collectors.toList());
        System.out.println(scores);
    }

    /** Stream lambda表达式方式 */
    @After
    public void testStreamLambda() throws Exception {
        //        Stream.of(list).filter();--->Stream的类型不一样
        List<Integer> scores = list.stream().filter(u -> u.getScore() > 85)
                // ???????? 这里的user.getScore怎么可以成为方法引用.1.getScore不是静态方法 2. getScore()没有参数列表而Function的apply有参数列表 ????????
                // 解: 特定类的任意对象的方法引用
                .sorted(Comparator.comparing(User::getScore).reversed())
                .map(u -> u.getScore())
                .collect(Collectors.toList());
        System.out.println(scores);
    }
}
