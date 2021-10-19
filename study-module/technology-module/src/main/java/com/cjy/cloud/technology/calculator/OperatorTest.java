package com.cjy.cloud.technology.calculator;

import com.cjy.cloud.technology.calculator.newidea.AddCalculator;
import java.util.Scanner;

/**
 * @author chenjingyu
 * @date 2021/9/19 22:32
 * @depiction 类功能简述
 */
public class OperatorTest {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("请输入数字1");
        Double x1 = in.nextDouble();
        System.out.println("请输入数字2");
        Double x2 = in.nextDouble();
        System.out.println("请输入运算符");
        String operator = in.next();
        System.out.println(Calculator.getResult(x1, x2, Operator.getOperator(operator)));
    }
}
