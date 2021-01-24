package com.chengxiaoxiao.strategy;

/**
 * GroupbuyStrategy
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 16:05
 */
public class GroupbuyStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.printf("拼团，满20人享受团购");
    }
}
