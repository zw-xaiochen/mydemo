package com.cjy.cloud.technology.calculator.supermarket.discount;

import com.cjy.cloud.technology.calculator.supermarket.ShoppingCart;
import com.cjy.cloud.technology.calculator.supermarket.commodity.AbstractCommodity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author chenjingyu
 * @date 2021/9/21 0:27
 * @depiction 折扣上下文
 */
public class DiscountContext {
    private int dayOfWeek;

    DiscountContext() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.dayOfWeek = localDateTime.getDayOfWeek().getValue();
    }

    public DiscountContext(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getResult(List<AbstractCommodity> commodities) {
        Double result = null;
        switch (dayOfWeek) {
            case 2:
            case 4:
                DirectDiscount discount = new DirectDiscount(0.8);
                // 先计算出总价,再进行打折
                result = discount.getResult(getCountValue(commodities));
            case 5:
                // 先计算出总价,再进行满减 满500减100
                ThresholdSubDiscount subDiscount = new ThresholdSubDiscount(500, 100);
                result = subDiscount.getResult(getCountValue(commodities));

            case 6:
                // 商品归类,生鲜类原价计算,非生鲜类9折,计算出总价之后再进行满减
            case 7:
                // 商品归类,生鲜类商品打八折并满500减200,非生鲜类9折并满300减100
                result = null;
        }
        return result;
    }

    private Double getCountValue(List<AbstractCommodity> commodities) {
        if (CollectionUtils.isEmpty(commodities)) {
            return 0.0;
        }
        return commodities.stream().mapToDouble(AbstractCommodity::getPrice).sum();
    }

    public static void main(String[] args) {
        //int first = 1;
        //int second = 2;
        //int third = 0; // 跳法sum
        //for(int i = 3; i <= 4; i++) {
        //    third = first + second;
        //    first = second;
        //    second = third;
        //    // 第一次循环计算出上第三个台阶的方法和 为3
        //    // 第二个循环计算出
        //}
        //System.out.println(third);
        int sum = jumpFloor(20);
        System.out.println(sum);
    }

    public static int jumpFloor(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            System.out.println("调用了sum");
            int sum = jumpFloor(target-1) + jumpFloor(target-2);
            return sum;
        }
    }

}
