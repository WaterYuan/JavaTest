package com.aaa.javatest.map;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(11, "十一");

        System.out.println("30 =" + Integer.toBinaryString(30));
        System.out.println("32 =" + Integer.toBinaryString(32));

        System.out.println("tableSizeFor=" + tableSizeFor(30));
        System.out.println("getCapacity=" + getCapacity(30));
        /*
30 =11110
32 =100000
--------
cap - 1 =11101
n >>> 1 =1110
n |= n >>> 1 =11111
tableSizeFor=32
getCapacity=32
         * */
        reflect();
    }

    public static void reflect() {
        try {
            System.out.println("");
            Class<Map> mapClass = Map.class;
            Class<HashMap> hashMapClass = HashMap.class;
            System.out.println("mapClass=" + mapClass);
            System.out.println("hashMapClass=" + hashMapClass);
            Class<?> classFromName = Class.forName("java.util.HashMap");
            System.out.println("classFromName=" + classFromName);
            HashMap<Integer, String> map = new HashMap();
            Class<? extends Map> classFromGet = map.getClass();
            System.out.println("classFromGet=" + classFromGet);
            /*
mapClass=interface java.util.Map
hashMapClass=class java.util.HashMap
classFromName=class java.util.HashMap
classFromGet=class java.util.HashMap
             * */

            System.out.println("");
            Field threshold = classFromGet.getDeclaredField("threshold");
            Field size = classFromGet.getDeclaredField("size");
            System.out.println("threshold=" + threshold);
            System.out.println("size=" + size);
            threshold.setAccessible(true);
            size.setAccessible(true);
            // Exception in thread "main" java.lang.IllegalArgumentException: Can not set int field java.util.HashMap.size to java.lang.Class
            // int thresholdInt = threshold.getInt(classFromGet);
            {
                int thresholdInt = threshold.getInt(map);
                int sizeInt = size.getInt(map);
                System.out.println("thresholdInt=" + thresholdInt);
                System.out.println("sizeInt=" + sizeInt);
            }
            System.out.println("添加第一个元素");
            map.put(1, "1");
            /*
thresholdInt=0
sizeInt=0

thresholdInt=12
sizeInt=1
             * */
            {
                int thresholdInt = threshold.getInt(map);
                int sizeInt = size.getInt(map);
                System.out.println("thresholdInt=" + thresholdInt);
                System.out.println("sizeInt=" + sizeInt);
            }

            System.out.println("添加11个元素");
            map.clear();
            for (int i = 1; i < 12; i++) {
                map.put(i, "" + i);
            }
            map.put(12, "12");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        System.out.println("--------");
        int n = cap - 1;
        System.out.println("cap - 1 =" + Integer.toBinaryString(n));
        int m = n;
        System.out.println("n >>> 1 =" + Integer.toBinaryString(m >>> 1));
        n |= n >>> 1;
        System.out.println("n |= n >>> 1 =" + Integer.toBinaryString(n));
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int getCapacity(int initialCapacity) {
        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        return capacity;
    }
}
