package com.chengxiaoxiao.strategy;

/**
 * CouponStrategy
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 15:57
 */
public class CouponStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.printf("优惠券形式，直接抵扣");
    }
}
