package com.cjy.cloud.technology.apitest;

import java.time.LocalDateTime;

public class LocalTimeTest {

    public static void main(String[] args) {
        // 增加指定周数
        LocalDateTime localDateTime = LocalDateTime.now().plusWeeks(1);
        System.out.println(localDateTime);
        //localDateTime.plusWeeks(1);

    }
}
