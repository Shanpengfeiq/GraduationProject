package com.siro.mall.controller.mall;

import com.siro.mall.common.Constants;
import com.siro.mall.common.MallException;
import com.siro.mall.common.ServiceResultEnum;
import com.siro.mall.dao.GoodsMapper;
import com.siro.mall.entity.Goods;
import com.siro.mall.service.CategoryService;
import com.siro.mall.service.GoodsMarkService;
import com.siro.mall.service.GoodsService;
import com.siro.mall.utils.BeanUtil;
import com.siro.mall.utils.PageQueryUtil;
import com.siro.mall.vo.GoodsDetailVO;
import com.siro.mall.vo.GoodsMarkVO;
import com.siro.mall.vo.SearchPageCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author shan
 * @date 2025-03-23
 */
@Api(tags = "前台商品controller")
@Controller
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private GoodsMarkService goodsMarkService;
    @Resource
    private GoodsMapper goodsMapper;


    @ApiOperation(value = "首页搜索功能")
    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        //params输入的参数
        System.out.println("params = "+ params);
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        // 设置每页商品数量的限制
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        // 检查参数中是否包含有效的商品类别ID
        Long categoryId=null;
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            // 将商品类别ID转换为Long类型
            categoryId= Long.valueOf(params.get("goodsCategoryId") + "");
            // 调用服务获取与类别ID相关的搜索页面类别信息
            SearchPageCategoryVO searchPageCategoryVO = categoryService.getCategoriesForSearch(categoryId);
            // 如果获取到的类别信息不为空，则设置到请求属性中，以供后续使用
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
        //封装参数供前端回显
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        //对keyword做过滤 去掉空格
        if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        //搜索上架状态下的商品
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", goodsService.searchGoods(pageUtil));
        request.setAttribute("goodsCategoryId",categoryId);
        return "mall/search";
    }

    @ApiOperation(value = "根据商品id查询详情")
    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        //根据id查询商品
        Goods goods = goodsService.getGoodsById(goodsId);
        //判断商品是否哦为空
        if (goods == null) {
            MallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        //判断商品是否下架
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            MallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        //评论
        List<GoodsMarkVO> goodsMarkById = goodsMarkService.getGoodsMarkById(goodsId);
        request.setAttribute("goodsMarks", goodsMarkById);
        request.setAttribute("goodsDetail", goodsDetailVO);
        return "mall/detail";
    }


//    @GetMapping("/Chat2")
//    public List<String> Chat(@RequestParam("content1") String content1){
//        System.out.println("_______________"+content1);
//        return goodsMapper.executeDynamicQuery(content1);
//    }
}
