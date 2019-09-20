package com.aaa.javatest.string_test;

public class Split_test {
    public static void main(String[] args) {
        String s = "你好-世界";
        String[] split = s.split("-");
        System.out.println(split[0]);
        System.out.println(split[1]);

        new Split_test().test();
    }

    private void test() {
        System.out.println("***********************");
        String s = "你好-"; // 以-拆分，只有一个元素
        String[] split = s.split("-");
        System.out.println(split[0] + " length=" + split.length);
//        System.out.println(split[1]); // ArrayIndexOutOfBoundsException
    }
}
