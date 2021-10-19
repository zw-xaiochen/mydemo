package com.cjy.cloud.arithmetic;

/**
 * @author chenjingyu
 * @date 2021/9/1 20:57
 * @depiction 质数(在大于1的自然数中只能被1和本身整数的数叫质数)相关运算
 */
public class PrimeNumber {

    public static void main(String[] args) {
        int number = 17;
        boolean result = isPrimeNumber(number);
        if (result) {
            System.out.println(number + "是质数");
        } else {
            System.out.println(number + "不是质数");
        }
    }

    /**
     * 是否是质数
     */
    private static boolean isPrimeNumber(int number) {
        if (number < 1) {
            return false;
        }
        boolean result = true;
        for (int index = 2; index < number; index++) {
            if (number % index == 0) {
                result= false;
                break;
            }
        }
        return result;

    }
}
