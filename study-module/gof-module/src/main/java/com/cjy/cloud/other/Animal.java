package com.cjy.cloud.other;

import java.util.concurrent.Callable;

/**
 * @author chenjingyu
 * @date 2021/8/17 14:54
 * @depiction
 */
public class Animal implements Callable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        return "1234";
    }
}
