package com.cjy.cloud.other.TestExtends;

/**
 * @author chenjingyu
 * @date 2021/10/13 22:19
 * @depiction 类功能简述
 */
public class TeacherTest {
    public static void main(String[] args) {
        Teacher teacher = new ChineseTeacher();
        teacher.sayHello();
        teacher.teach();
    }
}
