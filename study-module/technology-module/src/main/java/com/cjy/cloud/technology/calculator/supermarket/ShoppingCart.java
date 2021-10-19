package com.cjy.cloud.technology.calculator.supermarket;

import com.cjy.cloud.technology.calculator.supermarket.commodity.AbstractCommodity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjingyu
 * @date 2021/9/20 21:47
 * @depiction 购物车类
 */
public class ShoppingCart {
    /**
     * 商品集合
     */
    private List<AbstractCommodity> commodities = new ArrayList<>();

    public List<AbstractCommodity> getCommodities() {
        return commodities;
    }

    /**
     * 添加商品
     */
    public void addCommodity(AbstractCommodity commodity) {
        commodities.add(commodity);
    }

}
