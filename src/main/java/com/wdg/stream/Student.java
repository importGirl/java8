package com.wdg.stream;

import java.util.Random;

/**
 * @author wangdg
 * @Description: 学生类
 * @date 2017-06-11 00:50:22
 */
public class Student {

    private String name;
    private Integer age;
    private Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Student() {
        this.name = Math.random() + "";
        this.age = new Random().nextInt(100)+15;
        this.score = new Random().nextDouble()*100;
    }
}
