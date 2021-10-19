package com.cjy.cloud.other.TestExtends;

/**
 * @author chenjingyu
 * @date 2021/10/16 21:26
 * @depiction 类功能简述
 */
public class ObjectC extends ObjectA {

    @Override
    public void gatherMonty(double money) {
        System.out.printf("C 老板给了%s块钱 感谢老板%n", money);
        setMonty(money);
    }


    public void sayBoosGood() {
        System.out.println("老板好");
    }
}
