package com.coding.sales.data;

import com.coding.sales.constants.Discounts;
import com.coding.sales.constants.Units;
import com.coding.sales.pojo.Commodity;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @ClassName Commodities
 * @Description TODO
 * @Author ningguangyuan
 * @Date 2019/7/2 下午5:24
 * @ModifyDate 2019/7/2 下午5:24
 * @Version 1.0
 */
public class Commodities {

    public static final HashMap<String,Commodity> commodities = new HashMap<String,Commodity>();

    static {
        /**
         * * 世园会五十国钱币册
         * 		* 编号：001001
         *             * 单位：册
         * 		* 价格：998.00元
         */
        Commodity commodity1 = new Commodity("世园会五十国钱币册", "001001", Units.BOOK,
                new BigDecimal("998.00"), null);
        commodities.put("001001",commodity1);
        /*
                * 编号：001002
                * 单位：盒
                * 价格：1380.00
                * 可使用9折打折券*/
        Commodity commodity2 = new Commodity("2019北京世园会纪念银章大全40g", "001002", Units.BOX,
                new BigDecimal("1380.00"), new Discounts[]{Discounts.NINEDISCOUNT});
        commodities.put("001002",commodity2);
        /* 招财进宝
         * 编号：003001
         * 单位：条
         * 价格：1580.00
         * 可使用95折打折券*/
        Commodity commodity3 = new Commodity("招财进宝", "003001", Units.BAR,
                new BigDecimal("1580.00"), new Discounts[]{Discounts.NINEFIVEDISCOUNT});

        commodities.put("003001",commodity3);

        /* 水晶之恋
                * 编号：003002
                * 单位：条
                * 价格：980.00
                * 参与满减：第3件半价，满3送1    --------*/

        Commodity commodity4 = new Commodity("水晶之恋", "003002", Units.BAR,
                new BigDecimal("980.00"), new Discounts[]{Discounts.FREEDISCOUNT});

        commodities.put("003002",commodity4);

        /*中国经典钱币套装
                * 编号：002002
                * 单位：套
                * 价格：998.00
                * 参与满减：每满2000减30，每满1000减10 --------*/


        Commodity commodity5 = new Commodity("中国经典钱币套装", "002002", Units.SET,
                new BigDecimal("998.00"), new Discounts[]{Discounts.FREEPRICEONE,Discounts.FREEPRICETWO});

        commodities.put("002002",commodity5);


        /*守扩之羽比翼双飞4.8g
                * 编号：002001
                * 单位：条
                * 价格：1080.00
                * 参与满减：第3件半价，满3送1  -------
                * 可使用95折打折券*/

        Commodity commodity6 = new Commodity("守扩之羽比翼双飞4.8g", "002001", Units.BAR,
                new BigDecimal("1080.00"), new Discounts[]{Discounts.NINEFIVEDISCOUNT});

        commodities.put("002001",commodity6);

        /**中国银象棋12g
                * 编号：002003
                * 单位：套
                * 价格：698.00
                * 参与满减：每满3000元减350, 每满2000减30，每满1000减10
                * 可使用9折打折券 */
        Commodity commodity7 = new Commodity("中国银象棋12g", "002003", Units.SET,
                new BigDecimal("698.00"), new Discounts[]{Discounts.FREEPRICEONE,Discounts.FREEPRICETWO,
                Discounts.FREEPRICETHREE,Discounts.NINEDISCOUNT});

        commodities.put("002003",commodity7);




    }


}
