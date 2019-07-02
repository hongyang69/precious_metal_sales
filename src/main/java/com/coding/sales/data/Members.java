package com.coding.sales.data;

import com.coding.sales.constants.CardType;
import com.coding.sales.pojo.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Users
 * @Description TODO
 * @Author ningguangyuan
 * @Date 2019/7/2 下午4:10
 * @ModifyDate 2019/7/2 下午4:10
 * @Version 1.0
 */
public class Members {

    public static final HashMap<String,Member> members = new HashMap<String,Member>();


    /**马丁,普卡,6236609999,9860
    王立,金卡,6630009999,48860
    李想,白金卡,8230009999,98860
    张三,钻石卡,9230009999,198860**/
    static {
        Member martin = new Member("马丁", CardType.NORMAL, "6236609999", 9860);
        Member wangLi = new Member("王立", CardType.NORMAL, "6630009999", 48860);
        Member liXiang = new Member("马丁", CardType.NORMAL, "8230009999", 98860);
        Member zhangSan = new Member("马丁", CardType.NORMAL, "9230009999", 198860);
        members.put("6236609999",martin);
        members.put("6630009999",wangLi);
        members.put("8230009999",liXiang);
        members.put("9230009999",zhangSan);
    }



}
