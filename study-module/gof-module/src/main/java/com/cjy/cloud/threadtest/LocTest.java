package com.cjy.cloud.threadtest;

/**
 * @author chenjingyu
 * @date 2021/9/1 21:35
 * @depiction 本线线程变量(ThreadLocal)测试
 */
public class LocTest {
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
         String String = "";
        local.set("123");
    }
}
