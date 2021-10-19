package com.cjy.cloud.other.TestExtends;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.map.ListOrderedMap;

/**
 * @author chenjingyu
 * @date 2021/10/13 21:47
 * @depiction 类功能简述
 */
public class Teacher implements Test1Interface{
    public String name;

    public int age;

    public void teach() {
        System.out.println("教书");
    }

    @Override
    public void sayHello() {
        System.out.println("say hello");
    }

    //public Test1Interface test1Interface = () -> {
   //
   //};
   //
   // public Test2Interface test2Interface = () -> 0.0;
   //
   // public void sayHelloOne() {
   //     this.test1Interface.sayHello();
   // }
   //
   // public double sayHelloTwo() {
   //     return this.test2Interface.sayHello();
   // }

    //@Override
    //public void sayHello() {
    //    Collection
    //}
}
