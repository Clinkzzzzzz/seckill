package com.ray.service.model;

import java.math.BigDecimal;

/**
 * 用户下单的交易模型
 */
public class OrderModel {
    private String id;//订单号

    private Integer userId;//下单用户id

    private Integer itemId;//购买的商品id

    private BigDecimal itemPrice;//购买时商品单价，以后商品价格变动也不影响订单，若prmoid非空则表示秒杀商品价格

    private Integer amount;//购买数量

    private BigDecimal orderPrice;//购买金额，若prmoid非空则表示秒杀商品购买金额

    private Integer promoId;//若非空则以秒杀商品方式下单

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
