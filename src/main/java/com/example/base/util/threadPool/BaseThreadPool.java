package com.example.base.util.threadPool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

//
public class BaseThreadPool {

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
        //可重用固定个数的线程池,定时及周期执行的线程池，单线程的线程池，定时及周期执行的线程池
        FIXED(1),
        CACHED(2),
        SINGLE(3),
        SCHEDULED(4);

        private final ExecutorService executorService;
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("threadPool-%d")
                .setDaemon(true).build();

        private SingletonEnum(Integer status) {
            switch (status) {
                case 1:
                    executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0L,
                            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
                    break;
                case 2:
                    executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
                            TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
                    break;
                case 3:
                    executorService = new ThreadPoolExecutor(1, 1, 0L,
                            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
                    break;
                default:
                    executorService = new ScheduledThreadPoolExecutor(corePoolSize,threadFactory);
                    break;
            }
        }

        public ExecutorService getInstance() {
            return executorService;
        }
    }

    public static ExecutorService getFixedInstance() {
        return SingletonEnum.FIXED.getInstance();
    }
    public static ExecutorService getCachedInstance() {
        return SingletonEnum.CACHED.getInstance();
    }
    public static ExecutorService getSingleInstance() {
        return SingletonEnum.SINGLE.getInstance();
    }
    public static ExecutorService getScheduledInstance() {
        return SingletonEnum.SCHEDULED.getInstance();
    }
}
