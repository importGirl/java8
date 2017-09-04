package com.wdg.InterfaceDefaultMethod;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
@FunctionalInterface
public interface IUsb {

    void standardUSB();

    default void isPort(){
        System.out.println("USB都是一个接口");
    }

    static void transmitData(){
        System.out.println("usb都能进行传输数据");
    }

}
