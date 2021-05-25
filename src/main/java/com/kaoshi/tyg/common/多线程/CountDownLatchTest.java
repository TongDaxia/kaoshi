package com.kaoshi.tyg.common.多线程;


        import java.util.concurrent.CountDownLatch;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        System.out.println("主线程开始  ……");
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行1");
                    Thread.sleep(300);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行11");
                    Thread.sleep(2000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行22");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es2.shutdown();

        System.out.println(Thread.currentThread().getName() + "等待……");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}