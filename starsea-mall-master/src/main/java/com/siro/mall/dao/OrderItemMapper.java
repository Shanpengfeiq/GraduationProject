package com.siro.mall.dao;

import com.siro.mall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface OrderItemMapper {

    int deleteByPrimaryKey(Long orderItemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long orderItemId);
    //根据订单id获取订单项列表
    List<OrderItem> selectByOrderId(Long orderId);

    //根据订单ids获取订单项列表
    List<OrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    //批量insert订单项数据
    int insertBatch(@Param("orderItems") List<OrderItem> orderItems);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}
