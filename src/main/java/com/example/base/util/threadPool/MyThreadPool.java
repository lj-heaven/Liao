package com.example.base.util.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//自定义线程池
public class MyThreadPool {

    /**
     * 最大可用的CPU核数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 核心线程数量大小，如果是CPU 密集型任务(N+1)，如果是I/O 密集型任务(2N)，N为CPU核心数
     */
    private static final int corePoolSize = CPU_COUNT * 2;

    /**
     * 线程池最大容纳线程数
     */
    private static final int maximumPoolSize = CPU_COUNT * 4;

    enum SingletonEnum {
        //单例
        INSTANCE;

        private ThreadPoolExecutor executorService;
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d")
                .setDaemon(true).build();

        private SingletonEnum() {
            executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L,
                    TimeUnit.MILLISECONDS, new ResizableCapacityLinkedBlockingQueue<>(100), threadFactory,new MyRejectedExecutionHandler());
        }

        public ThreadPoolExecutor getInstance() {
            return executorService;
        }
    }

    public static ThreadPoolExecutor getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
}
