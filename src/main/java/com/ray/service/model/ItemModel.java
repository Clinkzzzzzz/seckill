package com.ray.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemModel {
    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String title;//商品名

    @NotNull(message = "商品价格不能为空")
    @Min(value = 0 ,message = "商品价格必须大于0")
    private BigDecimal price;//价格

    @NotNull(message = "商品库存不能为空")
    private Integer stock;//库存

    @NotBlank(message = "商品描述不能为空")
    private String description;//描述

    private Integer sales;//销量

    @NotBlank(message = "商品图片不能为空")
    private String imgUrl;//图片url

    //使用聚合模型，如果promoModel不为空，则表示拥有还未结束的秒杀活动即status=1 or 2
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
