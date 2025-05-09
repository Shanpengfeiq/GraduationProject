package com.siro.mall.vo;

import com.siro.mall.entity.GoodsCategory;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索页面分类数据VO
 * @author shan
 * @date 2025-03-23
 */
public class SearchPageCategoryVO implements Serializable {
    /**
     * 当前分类所属的一级分类名称
     * 用于标识商品所属的最顶层分类
     */
    private String firstLevelCategoryName;

    /**
     * 二级分类列表
     * 包含当前分类下所有的二级子分类
     */
    private List<GoodsCategory> secondLevelCategoryList;

    /**
     * 当前二级分类的名称
     * 用于标识商品所属的二级分类
     */
    private String secondLevelCategoryName;

    /**
     * 三级分类列表
     * 包含当前二级分类下所有的三级子分类
     */
    private List<GoodsCategory> thirdLevelCategoryList;

    /**
     * 当前分类的名称
     * 用于标识商品所属的最具体分类
     */
    private String currentCategoryName;

    public String getFirstLevelCategoryName() {
        return firstLevelCategoryName;
    }

    public void setFirstLevelCategoryName(String firstLevelCategoryName) {
        this.firstLevelCategoryName = firstLevelCategoryName;
    }

    public List<GoodsCategory> getSecondLevelCategoryList() {
        return secondLevelCategoryList;
    }

    public void setSecondLevelCategoryList(List<GoodsCategory> secondLevelCategoryList) {
        this.secondLevelCategoryList = secondLevelCategoryList;
    }

    public String getSecondLevelCategoryName() {
        return secondLevelCategoryName;
    }

    public void setSecondLevelCategoryName(String secondLevelCategoryName) {
        this.secondLevelCategoryName = secondLevelCategoryName;
    }

    public List<GoodsCategory> getThirdLevelCategoryList() {
        return thirdLevelCategoryList;
    }

    public void setThirdLevelCategoryList(List<GoodsCategory> thirdLevelCategoryList) {
        this.thirdLevelCategoryList = thirdLevelCategoryList;
    }

    public String getCurrentCategoryName() {
        return currentCategoryName;
    }

    public void setCurrentCategoryName(String currentCategoryName) {
        this.currentCategoryName = currentCategoryName;
    }
}
