package com.cjy.cloud.technology.calculator.newidea;

/**
 * @author chenjingyu
 * @date 2021/9/20 0:01
 * @depiction 定义一个抽象计算器类,由子类去实现具体的算式
 */
public abstract class NewCalculator {

    private double number1;

    private double number2;


    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public double getNumber2() {
        return number2;
    }

    public abstract double getResult();
}
