package com.siro.mall.service;

import com.siro.mall.entity.Goods;
import com.siro.mall.utils.PageQueryUtil;
import com.siro.mall.utils.PageResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface GoodsService {
    //后台分页
    PageResult getGoodsPage(PageQueryUtil pageUtil);

    //添加商品
    String saveGoods(Goods goods);

    //批量新增商品数据
    void batchSaveGoods(List<Goods> newBeeMallGoodsList);

    //修改商品信息
    String updateGoods(Goods goods);

    //获取商品详情
    Goods getGoodsById(Long id);

    //批量修改销售状态(上架下架)
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    //商品搜索
    PageResult searchGoods(PageQueryUtil pageUtil);
    //减库存
    Integer reductionStockNum(Map<Long,Integer> goodsIdMap);
}
