package com.cjy.cloud.technology.calculator.supermarket.discount;

import com.cjy.cloud.technology.calculator.supermarket.commodity.AbstractCommodity;
import java.util.List;

/**
 * @author chenjingyu
 * @date 2021/9/21 0:45
 * @depiction 抽象折扣策略
 */
public interface AbstractDiscountStrategy {
    /**
     * 计算金额
     * @param commodities 商品集合
     * @return 所有商品的金额
     */
    Double calculateMoney(List<AbstractCommodity> commodities);
}
