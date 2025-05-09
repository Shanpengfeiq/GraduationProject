package com.siro.mall.vo;

import java.io.Serializable;

/**
 * 首页配置商品VO
 * @author shan
 * @date 2025-03-23
 */
public class IndexConfigGoodsVO implements Serializable {

    /**
     * 商品ID
     * 用于唯一标识一个商品
     */
    private Long goodsId;

    /**
     * 商品名称
     * 描述商品的名称信息
     */
    private String goodsName;

    /**
     * 商品简介
     * 简要介绍商品的特点和用途
     */
    private String goodsIntro;

    /**
     * 商品封面图片的路径或URL
     * 用于展示商品的外观或包装
     */
    private String goodsCoverImg;

    /**
     * 商品的销售价格
     * 表示该商品的售价，单位通常为分（取决于具体业务逻辑）
     */
    private Integer sellingPrice;

    /**
     * 商品标签
     * 用于标识商品的类别或特性，便于搜索和管理
     */
    private String tag;

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

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
