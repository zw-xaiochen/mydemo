package com.cjy.cloud.technology.mathematics;

/**
 * @author chenjingyu
 * @date 2021/9/25 17:15
 * @depiction
 *
 *     质数,在大于1的自然数种 只能被1或它本身整除的数
 *     合数,在大于1的自然数种, 除了只能被1和它本身整除,还能被其他数整除的数
 */
public class PrimeNumberTest  {
    public static void main(String[] args) {
        boolean isNumber = true;
        // 输出100以内的所有质数
        for (int i = 2; i <= 100; i++) {
            // 一个自然数不能被大于它的数整除
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                System.out.print(" " + i);
            } else {
                isNumber = true;
            }


        }
        //System.out.println(Math.sqrt(2));
    }

    /**
     * 判断一个数是质数还是合数
     * @param num
     */
    static boolean judgePrimeNum(int num) {
        if (num < 3) {
            return num > 1;
        }
        for (int index = 2; index < num; index++) {
            if (num % index == 0) {
                return false;
            }
        }
        return true;
    }
}
