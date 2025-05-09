package com.siro.mall.vo;

import java.io.Serializable;

/**
 * 订单详情页页面订单项VO
 * @author shan
 * @date 2025-03-23
 */
public class OrderItemVO implements Serializable {

    /**
     * 商品ID
     * 用于唯一标识一个商品
     */
    private Long goodsId;

    /**
     * 商品数量
     * 表示库存或购买的数量
     */
    private Integer goodsCount;

    /**
     * 商品名称
     * 描述商品的名称信息
     */
    private String goodsName;

    /**
     * 商品封面图片路径
     * 保存商品的主显示图片，通常用于列表展示
     */
    private String goodsCoverImg;

    /**
     * 商品售价
     * 表示商品的销售价格，单位通常为分（为了避免浮点数计算的精度问题）
     */
    private Integer sellingPrice;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
