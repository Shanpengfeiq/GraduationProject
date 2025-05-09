package com.siro.mall.controller.mall;

import com.siro.mall.dao.GoodsMapper;
import com.siro.mall.entity.Goods;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
