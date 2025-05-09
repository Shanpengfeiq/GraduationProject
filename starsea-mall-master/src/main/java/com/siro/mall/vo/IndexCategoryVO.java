package com.siro.mall.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 首页分类数据VO
 * @author shan
 * @date 2025-03-23
 */
public class IndexCategoryVO implements Serializable {
    /**
     * 商品类别ID
     * 用于唯一标识一个商品类别
     */
    private Long categoryId;

    /**
     * 商品类别级别
     * 表示商品类别的层级，如一级类别、二级类别等
     */
    private Byte categoryLevel;

    /**
     * 商品类别名称
     * 描述商品类别的名字
     */
    private String categoryName;
    /**
     * 二级类别列表
     */
    private List<SecondLevelCategoryVO> secondLevelCategoryVOS;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Byte categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SecondLevelCategoryVO> getSecondLevelCategoryVOS() {
        return secondLevelCategoryVOS;
    }

    public void setSecondLevelCategoryVOS(List<SecondLevelCategoryVO> secondLevelCategoryVOS) {
        this.secondLevelCategoryVOS = secondLevelCategoryVOS;
    }
}
