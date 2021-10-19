package com.cjy.cloud.other;

import cn.hutool.core.collection.ConcurrentHashSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author chenjingyu
 * @date 2021/8/16 12:57
 * @depiction 类功能简述
 */
public class OtherTest {


    public static void main(String[] args) {

        //split(12);
        //String s = "123";
        //System.out.println(s.hashCode());
        //int num = 10;
        //System.out.println(num);
        //Map map = new HashMap();
        //map.put()
        //map.keySet();
        //map.values();

        double s = 283.2 % 23.6;
        System.out.println(s);
    }

    public static int split(int number) {
        System.out.println("当前数字为:" + number);
        if (number > 1) {
            if (number % 2 != 0) {
                System.out.println("余数不为0");
                System.out.println(split((number + 1) / 2));
            }
            System.out.println(split(number / 2));
        }
        return number;
    }

    /**
     * 反转字符串的方法,基于二分法来实现
     */
    public static void reverseStr() {
        String str = "abcdefg";
        char[] strByt = str.toCharArray();
        // 使用二分法将数组倒叙排序
        // n表示数组的最大下标
        int n = strByt.length - 1;
        // 最大下标 - 1 / 2 表示循环次数以及二分之后第一部分元素的最后一个下标
        // 假设现在数组中有6个元素 最大小标为5  i的值就是2
        // 假设现在数组中有7个元素 最大下标为6  i的值就是2
        // 这里i的计算为什么是最大下标 - 1 / 2
        // 因为需要确保数组的元素不管是奇数或者偶数,都能正确找到需要交换的元素的下标
        for (int i = n - 1 >> 1; i >= 0; i--) {
            int k = n - i;
            char ci = strByt[i];
            char ck = strByt[k];
            // 交互
            strByt[i] = ck;
            strByt[k] = ci;
        }

        throw new NullPointerException();
        //for ()
        //System.out.println(strByt);
    }
}
