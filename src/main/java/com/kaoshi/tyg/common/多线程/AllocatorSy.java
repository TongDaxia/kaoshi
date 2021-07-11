package com.kaoshi.tyg.common.多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllocatorSy {


    private List<Object> als = new ArrayList<>();

    /**
     * 一次性申请所有资源
     * 如果已经被申请了，说明当前线程不能再申请，需要进行阻塞 wait
     * 有人释放资源的时候会通过 #notifyAll() 方法唤醒当前阻塞线程
     *
     * @param from
     * @param to
     */
    synchronized void apply(Object from, Object to) {
        // 经典写法
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {
                //处理异常
            }
        }
        als.add(from);
        als.add(to);
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }


    public static void main(String[] args) {

        AllocatorSy allocator = new AllocatorSy();
        Object from = new Object();
        Object to = new Object();

        //换成线程池
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {

            executor.execute(() -> {
                allocator.apply(from, to);

                System.out.println("当前线程获取锁成功：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                    System.out.println("当前线程工作中：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程工作完成：" + Thread.currentThread().getName());
                allocator.free(from, to);
            });

        }
    }


}
