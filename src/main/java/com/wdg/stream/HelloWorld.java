package com.wdg.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class HelloWorld {

    // 店铺属性
     static class Property {
        String  name;
        // 距离，单位:米
        Integer distance;
        // 销量，月售
        Integer sales;
        // 价格，这里简单起见就写一个级别代表价格段
        Integer priceLevel;
        public  Property(String name, int distance, int sales, int priceLevel) {
            this.name = name;
            this.distance = distance;
            this.sales = sales;
            this.priceLevel = priceLevel;
        }
        // getter setter 省略
    }
    public static void main(String[] args) {
        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
        Property p3 = new Property("永和大王", 580, 3000, 1);
        Property p4 = new Property("肯德基", 6000, 200, 4);
        List<Property> properties = Arrays.asList(p1, p2, p3, p4);
//        properties.stream().sorted((o1,o2)->o1.distance.compareTo(o2.distance)).findFirst();
        String name = properties.stream().sorted((o1, o2) -> o1.distance.compareTo(o2.distance)).findFirst().get().name;
        System.out.println(name);
    }
}
