
### Java中静态属性、静态方法能否被继承、被重写？为什么？

- 结论    
>静态属性、静态方法可以被继承、重写，只是没表现出多态性而已。

- 分析    
>1. Java 静态方法、静态属性属于静态绑定，声明为父类就访问父类的父类的静态方法、静态属性，声明为子类，就访问子类，如果子类不存在该静态方法、静态属性，就访问相应父类的。
>2. 多态这个概念是针对动态绑定的，正是由于动态绑定的存在，才能形成多态！静态属性和静态方法可以被继承可以被重写，只是非动态绑定而没表现出多态性。
>3. 根据《java编程思想》中的描述这是因为静态方法和静态属性没有采用动态绑定。具体表现就是，将子类实例向上转型则会调用到基类中的静态方法和属性，不转型就调用子类自身的静态方法和属性。编译器不推荐通过实例去调用静态方法和属性，因为这种调用方式容易造成混淆。

- 代码
```java

public class One {
    //静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
    public static String one_1 = "one";

    public static void oneFn() {
        System.out.println("oneFn");
    }
}

public class Two extends One {
    //空
}

public class Two2 extends One {
    public static String one_1 = "two";

    public static void oneFn() {

        System.out.println("TwoFn");
    }
}

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

``` 