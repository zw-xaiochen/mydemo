package com.cjy.util;

import java.util.Random;

/**
 * @author chenjingyu
 * @date 2022/4/26 14:32
 * @description TODO 请添加类描述
 */
public class RandomUtil {

    /**
     * 生成6位随机数
     */
    public static String createSmCode() {
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                rand.append(1 + new Random().nextInt(9));
            } else {
                rand.append(new Random().nextInt(10));
            }
        }
        return rand.toString();
    }

    public static void main(String[] args) {
        for (int index = 0; index < 100; index++) {
            System.out.println(createSmCode());
        }
    }
}
