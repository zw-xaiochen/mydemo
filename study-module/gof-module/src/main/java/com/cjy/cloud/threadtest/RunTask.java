package com.cjy.cloud.threadtest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjingyu
 * @date 2021/8/26 12:59
 * @depiction 类功能简述
 */
public class RunTask implements Runnable {
    private volatile ThreadTest test;

    @Override
    public void run() {
        synchronized (this) {


            // 输出当前线程的状态
            System.out.println(Thread.currentThread().getState().name());
        }

    }
}
