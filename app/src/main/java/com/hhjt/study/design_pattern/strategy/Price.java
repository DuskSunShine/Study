package com.hhjt.study.design_pattern.strategy;

/**
 * Created by SCY on 2018/8/30 at 16:53.
 */

public class Price {
    private AbsPriceStrategy.STRATEGY strategy;
    private PriceStrategy priceStrategy;
    public Price(AbsPriceStrategy.STRATEGY strategy) {
        if (priceStrategy==null){
            priceStrategy=new PriceStrategy();
        }
        this.strategy = strategy;

    }

    //最终价格
    public double finalPrice (double booksPrice) {
        return this.priceStrategy.computePrice(strategy,booksPrice);
    }
}
