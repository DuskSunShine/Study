package com.hhjt.study.design_pattern.strategy;

/**
 * Created by SCY on 2018/8/30 at 16:40.
 * 价格策略
 * 本网站可能对所有的高级会员提供每本20%的促销折扣；
 * 对中级会员提供每本10%的折扣；对初级会员没有折扣。
 * 根据描述，折扣是根据以下的几个算法中的一个进行的：
 *
 * 算法一：对初级会员没有折扣。
 * 算法二：对中级会员提供10%的促销折扣。
 * 算法三：对高级会员提供40%的促销折扣。
 */


/**
 * 最高层的接口，只能识别顾客类型，不关心具体折扣计算
 */
public interface AbsPriceStrategy {
    /**
     *
     * @param goodsPrice 商品原价
     * @return 商品计算后的价格
     */
    double computePrice(STRATEGY strategy,double goodsPrice);

    /**
     * 会员类型
     */
    public enum STRATEGY{
        ORIGINAL,L_VIP,H_VIP
    }
}
