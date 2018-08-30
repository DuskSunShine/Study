package com.hhjt.study.design_pattern.strategy;

/**
 * Created by SCY on 2018/8/30 at 16:57.
 * 实现{@link AbsPriceStrategy},具体实现折扣算法。
 */

public class PriceStrategy implements AbsPriceStrategy{


    @Override
    public double computePrice(STRATEGY strategy, double goodsPrice) {
        double price;
        switch (strategy){
            case H_VIP:
                price= goodsPrice*0.6;
                break;
            case L_VIP:
                price= goodsPrice*0.9;
                break;
            case ORIGINAL:
                price= goodsPrice;
                break;
                default:
                    price= goodsPrice;
                    break;
        }
        return price;
    }
}
