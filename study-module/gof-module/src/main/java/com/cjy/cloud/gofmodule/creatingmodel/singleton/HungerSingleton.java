package com.cjy.cloud.gofmodule.creatingmodel.singleton;

/**
 * @author chenjingyu
 * @date 2021/8/10 15:19
 * @depiction 单列-饿汉模式(特点: 在类初始化时就生成对象)
 */
public class HungerSingleton {
    private static final HungerSingleton INSTANCE = new HungerSingleton();

    /**
     * 构造函数私有化,避免外部实例化
     */
    private HungerSingleton() {

    }

    public static HungerSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        HungerSingleton hungerSingleton = HungerSingleton.getInstance();
        System.out.println("获取到单列对象:" + hungerSingleton.hashCode());
    }
}
