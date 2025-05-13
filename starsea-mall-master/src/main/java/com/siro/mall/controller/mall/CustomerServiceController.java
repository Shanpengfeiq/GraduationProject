package com.siro.mall.controller.mall;

import com.siro.mall.dao.GoodsMapper;
import com.siro.mall.entity.Goods;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author shan
 * @date 2025-03-23
 */
@Api(tags = "客服")
@RestController
public class CustomerServiceController {
    @Resource
    private GoodsMapper goodsMapper;

    @PostMapping("/Chat2")
    public List<Goods> Chat(@RequestBody String name){
        System.out.println("_______________"+name);
        return goodsMapper.executeDynamicQuery(name);
    }
}
