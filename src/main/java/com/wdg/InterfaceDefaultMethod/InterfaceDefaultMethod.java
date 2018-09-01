package com.wdg.InterfaceDefaultMethod;

import org.junit.Test;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class InterfaceDefaultMethod {

    /**
     * 总结:
     * 1. 接口的default方法可以通过实现或者继承来传递
     * 2. 实现类可以覆写default方法
     * 3. 接口的static的方法不能被继承或者实现传递,也不能进行覆写
     * 4. 接口中可以写方法
     */

    @Test
    public void testDefaultMethod(){
        new USBImpl().isPort();
        new USBImpl().standardUSB();
        System.out.println("===========================");
        new USB2Impl().standardUSB();
        new USB2Impl().isPort();
        IUsb.transmitData();
    }
}
