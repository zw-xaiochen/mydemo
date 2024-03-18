package com.cjy.util.whell;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;

import java.util.concurrent.TimeUnit;

public class TestTimingWheel {

    private static final HashedWheelTimer timingWheel = new HashedWheelTimer();


    private static void addTime() {
        Timeout timeOut = timingWheel.newTimeout(timeout -> {
            if (timeout.isCancelled()) {
                return;
            }
            System.out.println("超时啦");
        }, -100, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        addTime();
    }
}
