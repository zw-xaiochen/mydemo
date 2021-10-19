package com.cjy.cloud.gofmodule.creatingmodel.singleton;

/**
 * @author chenjingyu
 * @date 2021/8/10 14:44
 * @depiction 懒汉单例模式(特点 第一调用getInstrt方法时创建对象)
 */
public class LazySingleton {
    /**
     * volatile关键字可以禁止JVM进行指令重排
     */
    private static volatile LazySingleton lazySingleton;

    /**
     * 构造方法私有化 避免类在外部被实例化
     */
    private LazySingleton() {
    }

    public static synchronized LazySingleton getLazySingleton() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        LazySingleton lazySingleton = getLazySingleton();
        System.out.println("第一次获取到懒汉单列, hashcode:" + lazySingleton.hashCode());
        LazySingleton lazySingleton1 = getLazySingleton();
        System.out.println("第二次获取懒汉单列, hashcode:" + lazySingleton1.hashCode());
        if (lazySingleton == lazySingleton1) {
            System.out.println("两个对象相同");
        } else {
            System.out.println("两个对象不相同");
        }

        // 这里补充一点hashcode和equals的知识
        // hashcode相同,两个对象不一定相同(存在hash冲突)
        // equals相同,则两个对象的hashcode一定相同(equals相同 表示两个对象相同)
        // 两个对象相同,hashcode不一定相同,但equals一定相同
        //
        // 反之两个对象hashcode相同,
    }
}
