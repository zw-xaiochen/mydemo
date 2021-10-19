package com.cjy.cloud.other.TestExtends;

/**
 * @author chenjingyu
 * @date 2021/10/16 17:59
 * @depiction 类功能简述
 */
public abstract class AbstractInterface1 implements Test1Interface {
    abstract void speakEnglish();

    @Override
    public void sayHello() {
        System.out.println("默认的say hello方法");
    }
}
