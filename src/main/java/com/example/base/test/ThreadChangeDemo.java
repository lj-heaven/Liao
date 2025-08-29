package com.example.base.test;

import com.example.base.util.threadPool.MyThreadPool;
import com.example.base.util.threadPool.ResizableCapacityLinkedBlockingQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadChangeDemo {
    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    /**
     * 先提交任务给线程池，并修改线程池参数
     *
     * @throws InterruptedException
     */
    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(15);
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    countDownLatch.countDown();
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
        threadPoolStatus(executor, "改变之前");
        executor.setCorePoolSize(16);
//        executor.setMaximumPoolSize(10);
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();
        queue.setCapacity(100);
        executor.prestartAllCoreThreads();
        threadPoolStatus(executor, "改变之后");
        try {
            countDownLatch.await();
        } catch (Exception e) {
            System.out.println("任务中断");
        }
    }

    /**
     * 打印线程池状态
     * @param executor
     * @param name
     */
    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();
        System.out.println(sdf.format(new Date())+Thread.currentThread().getName() + "-" + name + "-:" +
                "核心线程数：" + executor.getCorePoolSize() +
                "活动线程数" + executor.getActiveCount() +
                "最大线程数" + executor.getMaximumPoolSize() +
                "线程池活跃度" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                "任务完成数" + executor.getCompletedTaskCount() +
                "队列大小" + (queue.size() + queue.remainingCapacity()) +
                "当前排队线程数" + queue.size() +
                "队列剩余大小" + queue.remainingCapacity() +
                "队列使用度" + divide(queue.size(), queue.size() + queue.remainingCapacity())
        );
    }

    /**
     * 保留两位小数
     * @param num1
     * @param num2
     * @return
     */
    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }

    /**
     * 自定义线程池
     *
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        return MyThreadPool.getInstance();
    }
}
