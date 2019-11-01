package com.aaa.javatest.thread_test;

import java.sql.SQLOutput;
import java.util.concurrent.*;

public class DelayQueue_test {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool(); // 向上转型
        /**
         * 接口 {@link Executor} 只有一个 {@link Executor#execute(Runnable)} 方法
         * 接口 {@link ExecutorService} 继承自 Executor
         * 抽象类 {@link AbstractExecutorService} 实现 ExecutorService
         * 类 {@link ThreadPoolExecutor} 继承自 AbstractExecutorService
         * */

        System.out.println("*************************延迟队列*******************************");
        final DelayQueue delayQueue = new DelayQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过offer添加元素");
                delayQueue.offer(new MyDelayedTask("task1", 10000)); // 不用比较
                delayQueue.offer(new MyDelayedTask("task2", 3900));
                delayQueue.offer(new MyDelayedTask("task3", 1900));
                delayQueue.offer(new MyDelayedTask("task4", 5900));
                delayQueue.offer(new MyDelayedTask("task5", 6900));
                delayQueue.offer(new MyDelayedTask("task6", 7900));
                delayQueue.offer(new MyDelayedTask("task7", 4900));

                /**
                 通过offer添加元素
                 task2 - task1 = -6100 // 与前一个比较，顺序变为 task2 task1
                 task3 - task2 = -2000 // 与已排好的顺序的第一个比较，顺序变为 task3 task2 task1
                 task4 - task1 = -4100 // 与已排好的顺序的最后一个比较，task4应放在task1之前
                 task4 - task3 = 4000 // 与已排好的顺序的第一个比较，task4应放在task3之后
                 task5 - task4 = 1001
                 task6 - task2 = 4001
                 task7 - task2 = 1001
                 。。。
                 * */

                MyDelayedTask task_8 = new MyDelayedTask("task_8", 5000);
                MyDelayedTask task_10 = new MyDelayedTask("task_10", 1000);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                delayQueue.offer(task_8);
                delayQueue.offer(task_10);
                delayQueue.offer(new MyDelayedTask("task_9", 7000));
            }
        }).start();

        new Thread(new Runnable() {
            long currentTimeMillis = System.currentTimeMillis();

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("通过take取出元素");
                        Delayed take = delayQueue.take(); // 会阻塞
                        System.out.println("延迟时间=" + (System.currentTimeMillis() - currentTimeMillis));
                        System.out.println(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}

/**
 * 自定义的延迟队列的元素
 * https://www.cnblogs.com/myseries/p/10944211.html
 */
class MyDelayedTask implements Delayed {
    private String name;
    private long start = System.currentTimeMillis();
    private long time;

    public MyDelayedTask(String name, long time) {
        this.name = name;
        this.time = time;
    }

    /**
     * 需要实现的接口，获得延迟时间
     * <p>
     * 剩余延迟时间 = 总延迟时间 - 当前时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // 该自定义延迟队列因为使用的start由构造方法传入，故剩余延迟时间与构造时有关，而非offer()时机
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序
     * <p>
     * 当前对象的延迟时间 - 另一个对象的延迟时间
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        System.out.println(this.name + " - " + ((MyDelayedTask) o).name + " = " + (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS)));
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MyDelayedTask{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}