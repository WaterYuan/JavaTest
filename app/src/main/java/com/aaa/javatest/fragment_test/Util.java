package com.aaa.javatest.fragment_test;

public class Util {

    private static Util instance = new Util();

    public static Util getInstance() {
        return instance;
    }

    private static String sFragStr = "A";

    public static void setFragStr(String fragStr) {
        sFragStr = fragStr;
    }

    public static String getFragStr() {
        return sFragStr;
    }
}
