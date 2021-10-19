package com.cjy.cloud.threadtest;

/**
 * @author chenjingyu
 * @date 2021/8/26 13:00
 * @depiction 类功能简述
 */
public class ThreadTest {
    public String msg = "hello world";

    private String changeMsg = "";

    private String redMsg = "";

    public void setMsg() {
        if ("hello world".equals(this.msg)) {
            this.msg = "1111";
            this.changeMsg = Thread.currentThread().getName() +
                System.currentTimeMillis() + " 对象值被改变";
        } else {
            this.redMsg = Thread.currentThread().getName() +
                System.currentTimeMillis() + " 读到了改变后的值";
        }
    }

    private Long number = 0L;

    private void setNumber() {
        this.number += 10;
    }

    public static void sayHello() {
        int i = 0;
        i++;
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();

        Thread thread = new Thread(() -> {
            threadTest.setNumber();
            threadTest.setMsg();

        });
        Thread thread1 = new Thread(() -> {
            //System.out.println(Thread.currentThread().getName());
            //try {
            //    Thread.sleep(10);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            threadTest.setNumber();
            threadTest.setMsg();



        });
        thread.start();
        thread1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadTest.changeMsg);
        System.out.println(threadTest.redMsg);
        System.out.println(threadTest.number);
    }
}
