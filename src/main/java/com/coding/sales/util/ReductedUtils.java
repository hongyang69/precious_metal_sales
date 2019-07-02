package com.coding.sales.util;

import java.math.BigDecimal;

/**
 * @ClassName ReductedRules
 * @Description TODO
 * @Author ningguangyuan
 * @Date 2019/7/2 下午5:57
 * @ModifyDate 2019/7/2 下午5:57
 * @Version 1.0
 */
public class ReductedUtils {


    public static final BigDecimal NINEFIVEDISCOUNT = new BigDecimal("0.95");
    public static final BigDecimal NINEDISCOUNT = new BigDecimal("0.90");

    //规则1：95折券：如果商品参与95折，则应收金额为95折
    public static BigDecimal discout() {

        return new BigDecimal("0.00");

    }
    //规则2：9折券：如果商品参与9折，则应收金额为9折满减（仅对满减商品使用）
    public static BigDecimal discoutForNine() {
        return new BigDecimal("0.00");
    }
    //规则3：每满3000元减350
    public static BigDecimal freeRuleOne() {
        return new BigDecimal("0.00");
    }
    //规则4：每满2000元减30
    public static BigDecimal freeRuleTwo() {
        return new BigDecimal("0.00");
    }
    //规则5：每满1000元减10
    public static BigDecimal freeRuleThree() {
        return new BigDecimal("0.00");
    }
    //规则6：第3件半价（买3件及以上，其中1件半价）
    public static BigDecimal freeRuleFour() {
        return new BigDecimal("0.00");
    }
    //规则7：满3送1（买4件及以上，其中1件免费）
    public static BigDecimal freeRuleFive() {
        return new BigDecimal("0.00");
    }

}
