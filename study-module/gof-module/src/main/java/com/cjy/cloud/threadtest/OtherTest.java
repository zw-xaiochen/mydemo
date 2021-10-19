package com.cjy.cloud.threadtest;

/**
 * @author chenjingyu
 * @date 2021/9/1 23:51
 * @depiction 类功能简述
 */
public class OtherTest {

    public static int number = 10;

    private static void setNumber() {
        number += 1;
    }

    private static void initNumber() {
        number = 10;
    }

    private static int getNumber() {
        return number;
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();

        Thread thread = new Thread(() -> {
            synchronized (test) {
                setNumber();
            }
        });


        Thread thread1 = new Thread(() -> {
            setNumber();
        });
        thread.start();
        thread1.start();
        //for (int index = 0; index < 50; index++) {
        //    Thread thread = new Thread(() -> {
        //
        //        synchronized (test) {
        //            setNumber();
        //            test.setMsg();
        //
        //        }
        //
        //    });
        //    thread.start();
        //}

        //Thread thread1 = new Thread(() -> {
        //    setNumber();
        //    //synchronized (test) {
        //    //    test.setMsg();
        //    //
        //    //}
        //});
        //Thread thread2 = new Thread(() -> {
        //    setNumber();
        //});
        //thread.start();
        //thread1.start();
        //thread2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int number = getNumber();
        if (number == 12) {
            initNumber();
            main(args);
        } else {
            System.out.println("出现线程安全问题:" + number);
        }
        //System.out.println(test.msg);
        //System.out.println(getNumber());

    }
}
