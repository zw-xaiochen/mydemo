package com.cjy.cloud.other.TestExtends;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenjingyu
 * @date 2021/10/16 21:21
 * @depiction 类功能简述
 */
public class BoosTest {
    public static void main(String[] args) {
        Boos boos = new Boos();
        //ObjectA objectA = new ObjectA();
        //boos.giveMoney(objectA);
        //ObjectA objectC = new ObjectC();
        //boos.giveMoney(objectC);
        //ObjectA objectD = new ObjectD();
        //boos.giveMoney(objectD);
        //System.out.printf("A共有%s块钱\n",objectA.getMoney());
        //System.out.printf("C共有%s块钱\n",objectC.getMoney());
        //System.out.printf("D共有%s块钱",objectD.getMoney());

        Collection<String> collection = new ArrayList<>();
        collection.add("1");

        boos.saveCollection(collection);

    }
}
