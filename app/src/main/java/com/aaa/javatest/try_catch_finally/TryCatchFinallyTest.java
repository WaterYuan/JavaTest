package com.aaa.javatest.try_catch_finally;

import java.util.ArrayList;
import java.util.List;

public class TryCatchFinallyTest {

    public static void main(String[] args) {
        TryCatchFinallyTest tryCatchFinallyTest = new TryCatchFinallyTest();
        int testInt = tryCatchFinallyTest.testInt();
        System.out.println("testInt=" + testInt);

        String testString = tryCatchFinallyTest.testString();
        System.out.println("testString=" + testString);

        List<String> testList = tryCatchFinallyTest.testList();
        System.out.println("testList=" + testList);

    }

    private List<String> testList() {
        List<String> list = new ArrayList<>();
        try {
            list.add("try");
            System.out.println("1 " + list);
            return list;
        } catch (Exception e) {
            list.add("catch");
            System.out.println("2 " + list);
            return list;
        } finally {
            list.add("finally");
            System.out.println("3 " + list);
            list = new ArrayList<>();
            list.add("new");
            System.out.println("4 " + list);
            // return list;
        }
    }

    private String testString() {
        try {
            return "try";
        } catch (Exception e) {
        } finally {
            return "finally";
        }
    }

    private int testInt() {
        try {
            return 1;
        } catch (Exception e) {
        } finally {
            return 3;
        }
        // return 0; // Unreachable statement
    }
}
