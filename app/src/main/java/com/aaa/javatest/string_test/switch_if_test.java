package com.aaa.javatest.string_test;

public class switch_if_test {
    public static void main(String[] args) {
        String type = "A";
        switch (type) {
            case "A":
                if (true) {
                    System.out.println("true"); // 会被执行
                    if (false) {
                    } else {
                        break;
                    }
                    System.out.println("false"); // 不会被执行
                }
                break;
        }
    }

}
