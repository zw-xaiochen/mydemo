package com.cjy.cloud.gofmodule.creatingmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author chenjingyu
 * @date 2021/8/11 9:26
 * @depiction java内存模型测试
 */
public class MemoryTest extends Thread {
    private static boolean flag = true;

    @Override
    public void run() {
        int counter = 0;
        while (flag) {
            counter += 1;
            if (counter % 10000 == 0) {
                Thread.yield();
                //System.out.println("子线程尝试让出CPU");
            }

        }
    }

    public static void main(String[] args) {
        MemoryTest thread = new MemoryTest();
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println("主线程执行完成了");
    }
}
