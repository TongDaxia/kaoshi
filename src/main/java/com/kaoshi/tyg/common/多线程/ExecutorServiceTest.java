package com.kaoshi.tyg.common.多线程;


import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    static ExecutorService executor;


    @Bean
    private static ExecutorService orderExecutor(int nThreads) {
        Object createLock = new Object();

        if (executor == null) {
            synchronized (createLock) {
                if (executor == null) {
                    System.out.println(" ExecutorPoolUtil.execute -线程池初始化");
                    executor = new ThreadPoolExecutor(nThreads, // 核心线程数
                            nThreads,// 最大线程数
                            Integer.MAX_VALUE, // 闲置线程存活时间
                            TimeUnit.MILLISECONDS,// 时间单位
                            new LinkedBlockingQueue<Runnable>(200));//自定义容量大小 /线程队列
                }
            }
        }
        return executor;
    }


}
