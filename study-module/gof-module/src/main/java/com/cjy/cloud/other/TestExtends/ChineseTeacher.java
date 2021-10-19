package com.cjy.cloud.other.TestExtends;

/**
 * @author chenjingyu
 * @date 2021/10/13 22:11
 * @depiction 类功能简述
 */
public class ChineseTeacher extends Teacher implements Test2Interface{

    ChineseTeacher() {

    }

    ChineseTeacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public double sayHello1() {
        return 0;
    }
}
