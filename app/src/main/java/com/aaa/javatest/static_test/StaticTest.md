
### Java中静态属性、静态方法能否被继承、被重写？为什么？

- 结论    
>静态属性、静态方法可以被继承、重写，只是没表现出多态性而已。

- 分析    
>1. Java 静态方法、静态属性属于静态绑定，声明为父类就访问父类的父类的静态方法、静态属性，声明为子类，就访问子类，如果子类不存在该静态方法、静态属性，就访问相应父类的。
>2. 多态这个概念是针对动态绑定的，正是由于动态绑定的存在，才能形成多态！静态属性和静态方法可以被继承可以被重写，只是非动态绑定而没表现出多态性。
>3. 根据《java编程思想》中的描述这是因为静态方法和静态属性没有采用动态绑定。具体表现就是，将子类实例向上转型则会调用到基类中的静态方法和属性，不转型就调用子类自身的静态方法和属性。编译器不推荐通过实例去调用静态方法和属性，因为这种调用方式容易造成混淆。
>4. 实例去调用静态方法，容易混淆，请注意下图流程

![实例调用静态方法.PNG](https://upload-images.jianshu.io/upload_images/9601136-a3ae7a450eb7183d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)

- 代码
```java

public class One {
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
oneFn // 因为向上转型，故直接调用父类的
One.one_1>>>>>>>one
one.one_1>>>>>>>one
不转型直接调用 继承 的静态*********
oneFn // 虽然未向上转型，但子类只是继承未重写，故还是调用父类的
One.one_1>>>>>>>one
one.one_1>>>>>>>one
向上转型 调用 重写 的静态*********
oneFn // 虽然已重写，但因为向上转型，故直接调用父类的
One.one_1>>>>>>>one
one.one_1>>>>>>>one
不转型直接调用 重写 的静态*********
TwoFn // 已重写，且未向上转型，故直接调用自己的
One.one_1>>>>>>>one // 父类类对象直接调用父类的
one.one_1>>>>>>>two // 已重写，且未向上转型，调用自己的
*/
}

``` 
