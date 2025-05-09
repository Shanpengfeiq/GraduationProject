package com.siro.mall.service;

import com.siro.mall.entity.IndexConfig;
import com.siro.mall.utils.PageQueryUtil;
import com.siro.mall.utils.PageResult;
import com.siro.mall.vo.IndexConfigGoodsVO;

import java.util.List;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface IndexConfigService {
    PageResult getConfigsPage(PageQueryUtil pageUtil);

    String saveIndexConfig(IndexConfig indexConfig);

    String updateIndexConfig(IndexConfig indexConfig);

    IndexConfig getIndexConfigById(Long id);

    boolean deleteBatch(Long[] ids);

    //返回固定数量的首页配置商品对象(首页调用)
    List<IndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

    //随机刷新首页推荐
    List<IndexConfigGoodsVO> refreshConfigGoodsesForIndex(int configType, int number);

}
