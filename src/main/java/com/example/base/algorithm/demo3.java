package com.example.base.algorithm;

/**
 * 用两个线程交替打印数字1-100，一个打印奇数，一个打印偶数
 */
public class demo3 {

    private static final int END = 100;
    private static int START = 1;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (START <= END) {
                synchronized (lock) {
                    if(START % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ":" + START++);
                        lock.notify();
                    }else{
                        try{
                            lock.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "奇数线程");

        Thread evenThread = new Thread(() -> {
            while (START <= END) {
                synchronized (lock) {
                    if(START % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" + START++);
                        lock.notify();
                    }else{
                        try{
                            lock.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "偶数线程");

        oddThread.start();
        evenThread.start();
    }

}
