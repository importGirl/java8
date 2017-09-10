package com.wdg.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdg
 * @Description: 学校类
 * @date 2017-06-11 00:50:22
 */
public class School {
    private String name;
    private List<Class> classes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public School() {
        this.name ="广州市第80中学";
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", classes=" + classes +
                '}';
    }
}
