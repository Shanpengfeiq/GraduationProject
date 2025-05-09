package com.siro.mall.dao;

import com.siro.mall.entity.GoodsCategory;
import com.siro.mall.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long categoryId);

    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    List<GoodsCategory> findGoodsCategoryList(PageQueryUtil pageUtil);

    int getTotalGoodsCategories(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
    /**
     * 根据级别、父类别ID列表和数量限制选择商品类别
     *
     * @param parentIds 商品类别的父ID列表，用于筛选符合条件的类别
     * @param categoryLevel 类别的级别，用于进一步筛选符合条件的类别
     * @param number 限制返回的类别数量，确保结果集不会过大
     *
     * 此方法旨在通过特定的父类别ID列表、类别级别和数量限制来查询商品类别
     * 它通常用于获取特定层级下的子类别，以便进行分类展示或数据统计
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds, @Param("categoryLevel") int categoryLevel, @Param("number") int number);
}