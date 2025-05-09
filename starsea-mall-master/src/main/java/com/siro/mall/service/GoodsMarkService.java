package com.siro.mall.service;

import com.siro.mall.entity.GoodsMark;
import com.siro.mall.vo.GoodsMarkVO;

import java.util.List;

public interface GoodsMarkService {
    List<GoodsMarkVO> getGoodsMarkById(Long goodsMarkId);
    int saveGoodsMark(GoodsMark goodsMark);
}
