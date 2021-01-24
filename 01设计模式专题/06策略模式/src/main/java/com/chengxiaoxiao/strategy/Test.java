package com.chengxiaoxiao.strategy;

/**
 * Test
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 16:07
 */
public class Test {
    public static void main(String[] args) {
        PromotionActivity promotionActivity = new PromotionActivity(new CouponStrategy());
        promotionActivity.execute();


        /**
         * 如果增加新的优惠类，需要增加判断逻辑
         */
        PromotionActivity promotionActivity2 = null;

        String keys = "COUPON";
        if ("COUPON".equals(keys)) {
            promotionActivity2 = new PromotionActivity(new CouponStrategy());
        } else if ("GROUP".equalsIgnoreCase(keys)) {
            promotionActivity2 = new PromotionActivity(new GroupbuyStrategy());
        }

        promotionActivity2.execute();
    }
}
