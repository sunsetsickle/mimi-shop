package com.liang;

import com.liang.utils.MD5Util;

public class Test {

    @org.junit.Test
    public void test(){
        String mi= MD5Util.getMD5("123");
        System.out.println(mi);
    }
}
