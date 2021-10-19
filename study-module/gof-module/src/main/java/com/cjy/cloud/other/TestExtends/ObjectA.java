package com.cjy.cloud.other.TestExtends;

/**
 * @author chenjingyu
 * @date 2021/10/16 21:17
 * @depiction 类功能简述
 */
public class ObjectA {
    private double money;

    protected void setMonty(double money) {
        this.money += money;
    }

    public double getMoney() {
        return this.money;
    }


    public void gatherMonty(double money) {
        setMonty(money);
    }

}
