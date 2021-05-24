package com.kaoshi.tyg.service;

@SuppressWarnings("all")
public class LockTest {

    static Integer ss = 0;


    public static void main(String[] args) throws InterruptedException {
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        lock.lock();
//        boolean b = lock.tryLock();
//        System.out.println(b);
//        lock.unlock();
//        boolean b2 = lock.tryLock();
//        System.out.println(b2);
//
//        synchronized (lock) {
//            System.out.println("-------------------");
//            synchronized (lock) {
//                System.out.println("======================");
//            }
//        }

//        testLockLock();

        add2();


    }

    private static void testLockLock() throws InterruptedException {
        LockLock lockLock = new LockLock();
        lockLock.lock();
        System.out.println("locked。。。");

        lockLock.unlock();
        System.out.println("unLock。。。");

        lockLock.lock();
        System.out.println("locked again 。。。");


        Runnable thread = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockLock.unlock();
                System.out.println("unLock。。。");
                System.out.println("-------------------");
            }


        };
        thread.run();
    }


    private static void add2() throws InterruptedException {
        Integer count = 10000;
        LockLock lock = new LockLock();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    lock.lock();
                    ss += 1;
                    lock.unlock();
                }
            }

        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    lock.lock();
                    ss += 1;
                    lock.unlock();
                }
            }

        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("没有并发问题的话ss==" + count * 2);
        System.out.println("实际上ss=" + ss);
    }

    /**
     * 不可冲入锁
     */
    static class LockLock {
        volatile Boolean islock = false;

        synchronized void lock() {
            System.out.println("islock:" + islock);
            while (islock) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            islock = true;
        }

        synchronized void unlock() {
            while (islock) {
                islock = false;
            }
        }

    }

}
