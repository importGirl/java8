package com.wdg.interfaceDefaultMethod;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class USB2Impl implements IUsb2 {
    @Override
    public void standardUSB() {
        System.out.println("2.0接口");
    }
}
