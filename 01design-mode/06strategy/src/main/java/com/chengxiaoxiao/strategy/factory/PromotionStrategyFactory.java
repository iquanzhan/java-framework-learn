package com.chengxiaoxiao.strategy.factory;

import com.chengxiaoxiao.strategy.CouponStrategy;
import com.chengxiaoxiao.strategy.GroupbuyStrategy;
import com.chengxiaoxiao.strategy.PromotionActivity;
import com.chengxiaoxiao.strategy.PromotionStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 促销的策略工厂
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 16:12
 */
public class PromotionStrategyFactory {
    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<String, PromotionStrategy>();

    static {
        PROMOTION_STRATEGY_MAP.put("GROUP", new GroupbuyStrategy());
        PROMOTION_STRATEGY_MAP.put("COUPON", new CouponStrategy());
    }

    private PromotionStrategyFactory() {
    }

    public static PromotionStrategy getPromotionStrategy(String key) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(key);
        return promotionStrategy;
    }

    public static void main(String[] args) {
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy("COUPON"));
        promotionActivity.execute();
    }


}
