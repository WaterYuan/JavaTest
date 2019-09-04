package com.aaa.javatest.static_test;

public class StaticTest {

    //静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
    public static void main(String[] args) {
        new StaticTest().TwoTest();
        new StaticTest().Two_Test();
        new StaticTest().Two2Test();
        new StaticTest().Two2_Test();
    }

    /*
     * Two只是继承
     * */
    private void TwoTest() {
        System.out.println("向上转型 调用 继承 的静态*********");
        One one = new Two(); // 向上转型
        one.oneFn();
        String one_1 = One.one_1;
        System.out.println("One.one_1>>>>>>>" + one_1);
        String one_12 = one.one_1;
        System.out.println("one.one_1>>>>>>>" + one_12);
    }

    private void Two_Test() {
        System.out.println("不转型直接调用 继承 的静态*********");
        Two one = new Two(); // 不转型直接调用
        one.oneFn();
        String one_1 = One.one_1;
        System.out.println("One.one_1>>>>>>>" + one_1);
        String one_12 = one.one_1;
        System.out.println("one.one_1>>>>>>>" + one_12);
    }

    /*
     * Two2只是重写
     * */
    private void Two2Test() {
        System.out.println("向上转型 调用 重写 的静态*********");
        One one = new Two2(); // 向上转型
        one.oneFn();
        String one_1 = One.one_1;
        System.out.println("One.one_1>>>>>>>" + one_1);
        String one_12 = one.one_1;
        System.out.println("one.one_1>>>>>>>" + one_12);
    }

    private void Two2_Test() {
        System.out.println("不转型直接调用 重写 的静态*********");
        Two2 one = new Two2(); // 不转型直接调用
        one.oneFn();
        String one_1 = One.one_1;
        System.out.println("One.one_1>>>>>>>" + one_1);
        String one_12 = one.one_1;
        System.out.println("one.one_1>>>>>>>" + one_12);
    }

/*
向上转型 调用 继承 的静态*********
oneFn
One.one_1>>>>>>>one
one.one_1>>>>>>>one
不转型直接调用 继承 的静态*********
oneFn
One.one_1>>>>>>>one
one.one_1>>>>>>>one
向上转型 调用 重写 的静态*********
oneFn
One.one_1>>>>>>>one
one.one_1>>>>>>>one
不转型直接调用 重写 的静态*********
TwoFn
One.one_1>>>>>>>one
one.one_1>>>>>>>two
*/
}
