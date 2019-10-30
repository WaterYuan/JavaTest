package com.aaa.javatest.string_test;

public class switch_test {
    public static void main(String[] args) {
        String type = null;
        switch (type) {
            case "A":
                break;
        }
    }
    /*
Exception in thread "main" java.lang.NullPointerException
	at com.aaa.javatest.string_test.switch_test.main(switch_test.java:6)
     * */
}
