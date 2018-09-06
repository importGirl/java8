package com.wdg.share.stream;

/**
 * @author wangdg
 * @Description:
 * @date 2018/9/1 17:41
 */
public class Student {
    private String name;

    private Integer age;

    private Integer clas;

    private String school;

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

    public Integer getClas() {
        return clas;
    }

    public void setClas(Integer clas) {
        this.clas = clas;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clas=" + clas +
                ", school='" + school + '\'' +
                '}';
    }

    public Student(String name, Integer age, Integer clas, String school) {
        this.name = name;
        this.age = age;
        this.clas = clas;
        this.school = school;
    }

    public Student() {
    }
}
