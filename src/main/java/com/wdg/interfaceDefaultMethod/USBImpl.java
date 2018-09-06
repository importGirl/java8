package com.wdg.interfaceDefaultMethod;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class USBImpl implements IUsb {
    @Override
    public void standardUSB() {
        System.out.println("USB中间要有一横");
    }


}
