package com.kaoshi.tyg.common.多线程;


import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    //最大并行程度：10
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        //模拟100辆车进入停车场
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("====" + Thread.currentThread().getName() + "提交到集中处理器" + "====");
                        if (semaphore.availablePermits() == 0) {
                            System.out.println("当前处理能力不足，请稍后！！！");
                        }
                        //阻塞的获取资格
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "可以开始任务");
                        Thread.sleep(new Random().nextInt(10000));//任务进行中
                        System.out.println(Thread.currentThread().getName() + "任务完成");
                        semaphore.release();//释放资格
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, i + "任务");
            thread.start();
        }

    }
}