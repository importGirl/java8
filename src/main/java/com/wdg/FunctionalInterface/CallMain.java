package com.wdg.FunctionalInterface;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class CallMain {

    public static void main(String[] args) {
        IMobile mobile = ()->System.out.println("打电话给妈妈!!!");
        Iphone iphone = new Iphone();
        iphone.callPerson(mobile);
    }
}
