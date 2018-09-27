package com.wdg.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangdg
 * @Description: 班级类
 * @date 2017-06-11 00:50:22
 */
public class Class {
    private Integer classNo =0;
    private List<Student> students = new ArrayList<>();

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Class(Integer classNo) {
        Random random = new Random();
//        this.students = Stream.generate(Student::new).limit(random.nextInt(50)).collect(Collectors.toList());
        this.classNo = classNo;
    }

    public Class() {
    }

    @Override
    public String toString() {
        return "共有"+students.size()+"人"+"Class{" +
                "classNo=" + classNo +
                ", students=" + students +
                '}';
    }
}
