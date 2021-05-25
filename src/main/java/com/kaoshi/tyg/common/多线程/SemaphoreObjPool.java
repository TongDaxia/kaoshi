package com.kaoshi.tyg.common.多线程;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

@SuppressWarnings("all")
public class SemaphoreObjPool<T, R> {

    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;

    // 构造函数
    SemaphoreObjPool(int size) {
        pool = new Vector<T>();
        sem = new Semaphore(size);
    }

    // 利用对象池的对象，调用 func
    R exec(Function<T, R> func, T t) throws InterruptedException {
        sem.acquire();
        try {
            return func.apply(t);
        } finally {
            sem.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        SemaphoreObjPool<Object, String> pool = new SemaphoreObjPool<Object, String>(10);
        // 通过对象池获取 t，之后执行

        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    try {
                        String s = pool.exec(t ->
                        {
                            try {
                                System.out.println(Thread.currentThread().getName() + "开始任务");
                                System.out.println("参数：" + t + "==" + Thread.currentThread().getName());
                                Thread.sleep(new Random().nextInt(500));//任务进行中
                                System.out.println(Thread.currentThread().getName() + "任务完成");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            return t.toString();
                        }, Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //返回对象设置，一般是共享变量中保存
                }
            }.start();
        }


        pool.exec(t ->
        {
            System.out.println(t);
            return t.toString();
        }, "4567890-");
    }
}

