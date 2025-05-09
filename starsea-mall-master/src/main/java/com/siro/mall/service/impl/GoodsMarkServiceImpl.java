package com.siro.mall.service.impl;

import com.siro.mall.dao.GoodsMarkMapper;
import com.siro.mall.dao.UserMapper;
import com.siro.mall.entity.GoodsMark;
import com.siro.mall.entity.User;
import com.siro.mall.service.GoodsMarkService;
import com.siro.mall.service.UserService;
import com.siro.mall.utils.BeanUtil;
import com.siro.mall.vo.GoodsMarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GoodsMarkServiceImpl implements GoodsMarkService {
    @Resource
    private GoodsMarkMapper goodsMarkMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<GoodsMarkVO> getGoodsMarkById(Long goodsMarkId) {
        List<GoodsMark> goodsMarks = goodsMarkMapper.selectGoodsMarkList(goodsMarkId);
        List<GoodsMarkVO> goodsMarkVOList=new ArrayList<>();
        List<User> userList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsMarks)){
            for (GoodsMark item : goodsMarks) {
                User user = userMapper.selectByPrimaryKey(item.getUserId());
                GoodsMarkVO goodsMarkVO = new GoodsMarkVO();
                BeanUtil.copyProperties(item, goodsMarkVO);
                goodsMarkVO.setUserName(user.getNickName());
                goodsMarkVO.setUserAvatar(user.getAvatar());
                goodsMarkVOList.add(goodsMarkVO);
                userList.add(user);
            }
        }
        return goodsMarkVOList;
    }

    @Override
    public int saveGoodsMark(GoodsMark goodsMark) {
        if(goodsMarkMapper.saveGoodsMark(goodsMark)>0){
            return 1;
        }
        return 0;
    }
}
