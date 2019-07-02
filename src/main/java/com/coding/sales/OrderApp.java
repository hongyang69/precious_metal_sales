package com.coding.sales;

import com.coding.sales.constants.CardType;
import com.coding.sales.constants.Discounts;
import com.coding.sales.data.Commodities;
import com.coding.sales.data.Members;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.pojo.Commodity;
import com.coding.sales.pojo.Member;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {




    public static void main(String[] args) {
        /**if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }*/

        String jsonFileName = args[0];
        String txtFileName = args[1];

//        String jsonFileName = "sample_command11.json";
//        String txtFileName = "sample_result1.txt";

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);


//        return "".toString();

        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        //TODO: 请完成需求指定的功能

        /**
         * 满减（仅对满减商品使用）
         * 每满3000元减350
         * 每满2000元减30
         * 每满1000元减10
         * 第3件半价（买3件及以上，其中1件半价）
         * 满3送1（买4件及以上，其中1件免费）
         * 注：贵金属如果同时满足打折、满减，则只使用优惠力度最大的，不能同时使用。
         *
         */



        String memberId = command.getMemberId();    //获得用户ID
        String createTime = command.getCreateTime();
        String orderId = command.getOrderId();
        Member member = Members.members.get(memberId);
        List<String> discountCards = command.getDiscounts();

        List<OrderItemCommand> orderItemCommonds =  command.getItems();//商品列表
        List<PaymentCommand> payments = command.getPayments();//支付方式和积分
        PaymentCommand payment = payments.get(0);
        //List<String> memberDiscounts = command.getDiscounts();//获得打折信息
        String memberDiscount = command.getDiscounts().get(0);//获得打折信息

        List<PaymentRepresentation> paymentRepresentationLists = new ArrayList<PaymentRepresentation>();
        //获得Payment
        for (PaymentCommand payment1 : payments) {
            PaymentRepresentation paymentRepresentation = new PaymentRepresentation(payment1.getType(),payment1.getAmount());
            paymentRepresentationLists.add(paymentRepresentation);
        }


        List<OrderItemRepresentation>  orderItemRepresentationLists = new ArrayList<OrderItemRepresentation>();
        List<DiscountItemRepresentation>  discountItemRepresentationLists = new ArrayList<DiscountItemRepresentation>();


        double fullPrice = 0.00;
        for (OrderItemCommand orderItemCommand : orderItemCommonds) {

            String serialNum = orderItemCommand.getProduct();   //获得商品码

            Commodity commodity = Commodities.commodities.get(serialNum);   //根据商品码获得商品信息

            double numsOfCommodities = orderItemCommand.getAmount().doubleValue();

            Discounts[] prodDiscounts = commodity.getDiscounts();
            double minPrice = 0.00;

            minPrice = getMinPrice(commodity,payment, memberDiscount,numsOfCommodities,orderItemRepresentationLists,discountItemRepresentationLists);    //一个商品的最小价
            System.err.println("minPrice-" +  "-" + commodity.getPrice() + "-" + numsOfCommodities + "-" + minPrice) ;
            fullPrice += minPrice;


        }

        System.err.println(fullPrice);

        //result =new OrderRepresentation();



        double totalDiscount = 0.00;
        for (DiscountItemRepresentation  discountItemRepresentation : discountItemRepresentationLists) {
            totalDiscount += discountItemRepresentation.getDiscount().doubleValue();
        }
        double totalPrice = 0.00;
        for ( OrderItemRepresentation orderItemRepresentation: orderItemRepresentationLists) {
            totalPrice +=orderItemRepresentation.getPrice().multiply(orderItemRepresentation.getAmount()).doubleValue();
        }

        String cardType = "";
        int nowBonus = 0;
        if (member.getCardType().equals(CardType.NORMAL)) {
            cardType = "普卡";
            nowBonus = (int)fullPrice*1 + payment.getAmount().intValue();
        }else if (member.getCardType().equals(CardType.NORMAL)) {
            cardType = "金卡";
            nowBonus = (int)(fullPrice*1.5) + payment.getAmount().intValue();;
        } else if (member.getCardType().equals(CardType.NORMAL)) {
            cardType = "白金卡";
            nowBonus = (int)(fullPrice*1.8) + payment.getAmount().intValue();;
        } else if (member.getCardType().equals(CardType.NORMAL)) {
            cardType = "钻石卡";
            nowBonus = (int)(fullPrice*2) + payment.getAmount().intValue();
        }

        System.err.println("积分现在是：" + nowBonus);

        String currentCardType = "";
        if (nowBonus < 10000) {
            currentCardType = "普卡";
        } else if (nowBonus >= 10000 && nowBonus < 50000) {
            currentCardType = "金卡";
        } else if (nowBonus >= 50000 && nowBonus < 100000) {
            currentCardType = "白金卡";
        } else if (nowBonus >= 100000) {
            currentCardType = "钻石卡";
        } else {
            currentCardType = "普卡";
        }




        /**public OrderRepresentation(String orderId, Date createTime,
         String memberNo, String memberName, String oldMemberType, String newMemberType, int memberPointsIncreased, int memberPoints,
         List<OrderItemRepresentation> orderItems,
         BigDecimal totalPrice, List<DiscountItemRepresentation> discounts, BigDecimal totalDiscountPrice,
         BigDecimal receivables, List<PaymentRepresentation> payments, List<String> discountCards) **/

        Date date = null;

        try {
         date = new SimpleDateFormat("yyyy-MM-dd").parse(createTime);
        } catch (Exception e) {

        }

        result = new OrderRepresentation(orderId,date,memberId, member.getMemberName(),cardType,currentCardType,
                ((int)fullPrice), (int)fullPrice + payment.getAmount().intValue(),orderItemRepresentationLists,
                new BigDecimal(totalPrice),discountItemRepresentationLists,new BigDecimal(totalDiscount),new BigDecimal(fullPrice),
                paymentRepresentationLists,discountCards);


        return result;
    }



    /**
     * 根据打折获取最小金额 -- 针对一个商品
     */
    private double getMinPrice(Commodity commodity ,PaymentCommand payment, String memberDiscount,double numsOfCommodities ,List<OrderItemRepresentation> orderItemRepresentationLists,List<DiscountItemRepresentation>  discountItemRepresentationLists) {

        Discounts[] prodDiscounts = commodity.getDiscounts();   //商品打折信息

        double totalPrice = commodity.getPrice().doubleValue() * numsOfCommodities;



        double memberDiscountMinPrice = totalPrice; //取最小价格
        if (prodDiscounts != null) {
            for (Discounts prodDiscount : prodDiscounts) {
                double tempMemberDiscountMinPrice = 0.00;
                if (Discounts.NINEFIVEDISCOUNT.equals(prodDiscount) &&
                        ("9折券".equals(memberDiscount) || "95折券".equals(memberDiscount))) {//95折

                    if ("9折券".equals(memberDiscount)) {
                        tempMemberDiscountMinPrice = dicountForPrice(totalPrice, 0.9);
                    } if ("95折券".equals(memberDiscount)) {
                        tempMemberDiscountMinPrice = dicountForPrice(totalPrice, 0.95);
                    }

                } else if (Discounts.NINEDISCOUNT.equals(prodDiscount) &&
                        ("9折券".equals(memberDiscount))) {//9折
                    tempMemberDiscountMinPrice = dicountForPrice(totalPrice, 0.9);
                } else if (Discounts.FREEDISCOUNT.equals(prodDiscount) &&
                        numsOfCommodities >= 3) {//参与满减：第3件半价，满3送1
                    tempMemberDiscountMinPrice = dicountForOneFree(totalPrice, numsOfCommodities, commodity.getPrice().doubleValue());
                } else if (Discounts.FREEPRICEONE.equals(prodDiscount)) {//每满1000元减10
                    tempMemberDiscountMinPrice = freeForPrice(totalPrice, 3000, 350);
                } else if (Discounts.FREEPRICETWO.equals(prodDiscount)) {//每满2000元减30
                    tempMemberDiscountMinPrice = freeForPrice(totalPrice, 2000, 30);
                } else if (Discounts.FREEPRICETHREE.equals(prodDiscount)) {//每满3000元减350*/
                    tempMemberDiscountMinPrice = freeForPrice(totalPrice, 1000, 10);
                }

                //赋值
                if (memberDiscountMinPrice > tempMemberDiscountMinPrice) {
                    memberDiscountMinPrice = tempMemberDiscountMinPrice;
                }
            }
        } else {
            memberDiscountMinPrice = totalPrice;
        }

        //String productNo, String productName, BigDecimal price, BigDecimal amount, BigDecimal subTotal

        OrderItemRepresentation orderItemRepresentation = new OrderItemRepresentation(commodity.getSerialNum(), commodity.getComName(), commodity.getPrice(),
                new BigDecimal(numsOfCommodities), new BigDecimal(totalPrice));
        DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(commodity.getSerialNum(),commodity.getComName(),new BigDecimal(totalPrice-memberDiscountMinPrice));
        orderItemRepresentationLists.add(orderItemRepresentation);

        if (totalPrice != memberDiscountMinPrice) {//优惠是0时，不打印
            discountItemRepresentationLists.add(discountItemRepresentation);
        }


        return memberDiscountMinPrice;
    }






    //1、满减方法
    private double freeForPrice(double totalPrice, double disountPrice, double minusPrice) {
        if (totalPrice > disountPrice) {
              return totalPrice - ((int)(totalPrice / disountPrice)) * minusPrice;
        }
        return totalPrice;
    }

    //2、打折方法
    private double dicountForPrice(double totalPrice, double discount) {
//        return totalPrice * discount;
        return (new BigDecimal(totalPrice).multiply(new BigDecimal(discount))).doubleValue();
    }

    //3、满3半价，买4减1件yonghu
    // 参数 1：总价格 参数2：商品数量：参数3：商品单价
    private double dicountForOneFree(double totalPrice, double numsOfCommodities, double unitPrice) {

        if (numsOfCommodities == 3) {
            return totalPrice - (unitPrice * (numsOfCommodities - 0.5));
        } else if (numsOfCommodities >= 4) {
            return totalPrice - (unitPrice * (numsOfCommodities - 1));
        } else {
            return totalPrice;
        }
    }

}
