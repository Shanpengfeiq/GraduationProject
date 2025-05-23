package com.siro.mall.service.impl;

import com.siro.mall.common.Constants;
import com.siro.mall.common.MallCategoryLevelEnum;
import com.siro.mall.common.ServiceResultEnum;
import com.siro.mall.dao.GoodsCategoryMapper;
import com.siro.mall.entity.GoodsCategory;
import com.siro.mall.service.CategoryService;
import com.siro.mall.utils.BeanUtil;
import com.siro.mall.utils.PageQueryUtil;
import com.siro.mall.utils.PageResult;
import com.siro.mall.vo.IndexCategoryVO;
import com.siro.mall.vo.SearchPageCategoryVO;
import com.siro.mall.vo.SecondLevelCategoryVO;
import com.siro.mall.vo.ThirdLevelCategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author shan
 * @date 2025-03-23
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.findGoodsCategoryList(pageUtil);
        int total = goodsCategoryMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(goodsCategories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp != null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (goodsCategoryMapper.insertSelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory temp2 = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(goodsCategory.getCategoryId())) {
            //同名且不同id 不能继续修改
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        goodsCategory.setUpdateTime(new Date());
        if (goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return goodsCategoryMapper.deleteBatch(ids) > 0;
    }

    //todo
//    @Override
//    public List<IndexCategoryVO> getCategoriesForIndex() {
//        List<IndexCategoryVO> newBeeMallIndexCategoryVOS = new ArrayList<>();
//        //获取一级分类的固定数量的数据
//        // 选择第一级商品类别，这些类别具有特定的父ID、级别和数量
//        // 使用Collections.singletonList创建一个只包含0L的列表作为parentIds参数，表示顶级类别
//        // MallCategoryLevelEnum.LEVEL_ONE.getLevel()提供类别级别参数，选择第一级类别
//        // Constants.INDEX_CATEGORY_NUMBER提供数量参数，选择特定数量的类别
//        //1、获取所有一级父类的分类，就是parentId为0的分类
////        2、判断一级父类里面是否有值
////        3、如果一级父类有值，将一级分类的id通过流化处理赋值给一个集合里面，这样就可以通过一级分类的id获取到二级分类的数据
////        4、判断二级分类中是否有值
////        5、如果有值，遍历二级分类，获取二级分类的id，通过流处理将二级分类的id赋值给一个集合，然后通过集合获取三级分类的数据
////        6、如果三级分类有数据，我们要将三级分类的数据保存到二级分类中的List<ThirdLevelCategoryVO> thirdLevelCategoryVOS这个集合中
////        6.1、将三级分类的数据存储到map集合中，key是二级分类的id，value是三级分类的集合
////        6.2、循环二级分类，将三级分类的数据保存到二级分类中的List<ThirdLevelCategoryVO> thirdLevelCategoryVOS这个集合中
////        6.2.1、将二级分类完整数据集合复制给显示数据集合
////        6.2.2、判断二级分类下面是否还有数据，通过二级分类的id与map的key进行匹配
////        6.2.2.1、如果有数据，就从三级分类map中通过key(二级分类的id)的方式取出值，赋值给List<GoodsCategory>(获取到二级分类下对应的三级分类)
////        6.2.2.2、现在我们有二级分类和二级分类下对应的三级分类数据，前面已经将二级分类的前三个数据赋值了，现在要将三级分类的数据赋值上去
////        因为三级分类的数据是List<GoodsCategory>类型，而二级分类下面需要List<ThirdLevelCategoryVO>类型
////        所以要先将List<GoodsCategory>类型转化成List<ThirdLevelCategoryVO>类型通过流，最后添加到显示数据集合中
//        List<GoodsCategory> firstLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), MallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);
//
//        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
//            // 使用流处理从firstLevelCategories列表中提取每个元素的categoryId属性，并收集到一个新的列表中
//            //map里面参数需要一个function参数所以用::
//            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
//            //获取二级分类的数据
//            List<GoodsCategory> secondLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, MallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
//            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
//                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
//                //获取三级分类的数据
//                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(secondLevelCategoryIds, MallCategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
//                //将三级的各各id获取到
//
//                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
//                    //根据 parentId 将 thirdLevelCategories 分组
//                    //groupingBy：根据getParentId分类，map的key就是getParentId
//                    Map<Long, List<GoodsCategory>> thirdLevelCategoryMap = thirdLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
//                    //最终二级分类数据存储在这里
//                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
//                    //处理二级分类
//                    for (GoodsCategory secondLevelCategory : secondLevelCategories) {
//                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
//                        //将secondLevelCategory(完整分类信息)复制到secondLevelCategoryVO(主要分类信息，这个是要显示的数据)
//                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
//                        //如果该二级分类下有数据则放入 secondLevelCategoryVOS 对象中
//                        if (thirdLevelCategoryMap.containsKey(secondLevelCategory.getCategoryId())) {
//                            //根据二级分类的id取出thirdLevelCategoryMap分组中的三级分类list
//                            List<GoodsCategory> tempGoodsCategories = thirdLevelCategoryMap.get(secondLevelCategory.getCategoryId());
//
//                            secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyList(tempGoodsCategories, ThirdLevelCategoryVO.class)));
//                            secondLevelCategoryVOS.add(secondLevelCategoryVO);
//                        }
//                    }
//                    //处理一级分类
//                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
//                        //根据 parentId 将 secondLevelCategories 分组
//                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
//                        for (GoodsCategory firstCategory : firstLevelCategories) {
//                            IndexCategoryVO newBeeMallIndexCategoryVO = new IndexCategoryVO();
//                            BeanUtil.copyProperties(firstCategory, newBeeMallIndexCategoryVO);
//                            //如果该一级分类下有数据则放入 newBeeMallIndexCategoryVOS 对象中
//                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
//                                //根据一级分类的id取出secondLevelCategoryVOMap分组中的二级级分类list
//                                List<SecondLevelCategoryVO> tempGoodsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
//                                newBeeMallIndexCategoryVO.setSecondLevelCategoryVOS(tempGoodsCategories);
//                                newBeeMallIndexCategoryVOS.add(newBeeMallIndexCategoryVO);
//                            }
//                        }
//                    }
//                }
//            }
//            return newBeeMallIndexCategoryVOS;
//        } else {
//            return null;
//        }
//    }

    @Override
    public List<IndexCategoryVO> getCategoriesForIndex() {
        List<IndexCategoryVO> newBeeMallIndexCategoryVOS = new ArrayList<>();
        List<GoodsCategory> firstLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), MallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);

        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            // 使用流处理从firstLevelCategories列表中提取每个元素的categoryId属性，并收集到一个新的列表中
            //map里面参数需要一个function参数所以用::
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
            //获取二级分类的数据
            List<GoodsCategory> secondLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, MallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                Map<Long, List<GoodsCategory> > secondLevelCategoryMap = secondLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
                for(GoodsCategory firstLevelCategory : firstLevelCategories){
                    IndexCategoryVO newBeeMallIndexCategoryVO = new IndexCategoryVO();
                    BeanUtil.copyProperties(firstLevelCategory, newBeeMallIndexCategoryVO);
                    if(secondLevelCategoryMap.containsKey(firstLevelCategory.getCategoryId())){
                List<GoodsCategory> tempGoodsCategories = secondLevelCategoryMap.get(firstLevelCategory.getCategoryId());
                newBeeMallIndexCategoryVO.setSecondLevelCategoryVOS(BeanUtil.copyList(tempGoodsCategories, SecondLevelCategoryVO.class));
                newBeeMallIndexCategoryVOS.add(newBeeMallIndexCategoryVO);
                    }
                }
            }
            //打印newBeeMallIndexCategoryVOS集合的值
//            System.out.println(newBeeMallIndexCategoryVOS);
            return newBeeMallIndexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        SearchPageCategoryVO searchPageCategoryVO = new SearchPageCategoryVO();
        GoodsCategory thirdLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
        if (thirdLevelGoodsCategory != null && thirdLevelGoodsCategory.getCategoryLevel() == MallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            //获取当前三级分类的二级分类
            GoodsCategory secondLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(thirdLevelGoodsCategory.getParentId());
            if (secondLevelGoodsCategory != null && secondLevelGoodsCategory.getCategoryLevel() ==  MallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                //获取当前二级分类下的三级分类List
                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelGoodsCategory.getCategoryId()), MallCategoryLevelEnum.LEVEL_THREE.getLevel(), Constants.SEARCH_CATEGORY_NUMBER);
                searchPageCategoryVO.setCurrentCategoryName(thirdLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setSecondLevelCategoryName(secondLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setThirdLevelCategoryList(thirdLevelCategories);
                return searchPageCategoryVO;
            }
        }
        return null;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);//0代表查询所有
    }
}
