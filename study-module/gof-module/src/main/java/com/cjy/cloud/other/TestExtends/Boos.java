package com.cjy.cloud.other.TestExtends;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javafx.print.Collation;

/**
 * @author chenjingyu
 * @date 2021/10/16 21:17
 * @depiction 类功能简述
 */
public class Boos {


    public  void giveMoney(ObjectA objectA) {
         objectA.gatherMonty(10.0);
    }

    public void saveCollection(Collection<?> collation) {
        Class clas = collation.getClass();
        Class<?>[] array = clas.getInterfaces();
        System.out.println(Arrays.asList(array));
        System.out.printf("类名: %s", clas.getName());
    }
}
