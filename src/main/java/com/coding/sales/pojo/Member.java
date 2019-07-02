package com.coding.sales.pojo;

import com.coding.sales.constants.CardType;

/**
 * @ClassName User
 * @Description TODO
 * @Author ningguangyuan
 * @Date 2019/7/2 下午4:05
 * @ModifyDate 2019/7/2 下午4:05
 * @Version 1.0
 */
public class Member {

    private String memberName;    //姓名
    private CardType cardType;  //等级
    private String cardNo;      //卡号
    private int bonusPoints;    //积分



    public Member(String memberName, CardType cardType, String cardNo, int bonusPoints) {
        this.memberName = memberName;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.bonusPoints = bonusPoints;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
