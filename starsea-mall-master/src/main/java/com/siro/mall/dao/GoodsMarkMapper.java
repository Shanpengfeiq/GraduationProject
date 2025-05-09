package com.siro.mall.dao;

import com.siro.mall.entity.GoodsMark;

import java.util.List;

public interface GoodsMarkMapper {
    List<GoodsMark> selectGoodsMarkList(Long goodsId);
//    添加
    int saveGoodsMark(GoodsMark goodsMark);
}
