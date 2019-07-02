package com.coding.sales.pojo;

import com.coding.sales.constants.Discounts;

import java.math.BigDecimal;

/**
 * @ClassName   Commodity
 * @Description 商品类
 * @Author      QinHaiYin
 * @Date        2019/7/2 下午4:21
 * @Version     1.0
 */
public class Commodity {

    /**
     *      * 世园会五十国钱币册
     * 		* 编号：001001
     * 		* 单位：册
     * 		* 价格：998.00元
     *
     */
    private String comName;    //姓名
    private String serialNum;  //编号
    private String unit;    //单位
    private BigDecimal price;    //价格
    private Discounts[] discounts;

    public Commodity(String comName, String serialNum, String unit, BigDecimal price, Discounts[] discounts) {
        this.comName = comName;
        this.serialNum = serialNum;
        this.unit = unit;
        this.price = price;
        this.discounts = discounts;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Discounts[] getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Discounts[] discounts) {
        this.discounts = discounts;
    }
}
