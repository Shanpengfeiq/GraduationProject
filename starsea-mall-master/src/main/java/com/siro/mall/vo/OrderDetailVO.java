package com.siro.mall.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单详情页页面VO
 * @author shan
 * @date 2025-03-23
 */
public class OrderDetailVO implements Serializable {
    /**
     * 订单号，唯一标识一个订单
     */
    private String orderNo;
    /**
     * 总价格，表示订单的总金额
     */
    private Integer totalPrice;
    /**
     * 支付状态，用于记录订单的支付情况
     */
    private Byte payStatus;
    /**
     * 支付状态字符串，用于直观显示支付状态
     */
    private String payStatusString;
    /**
     * 支付类型，表示订单采用的支付方式
     */
    private Byte payType;
    /**
     * 支付类型字符串，用于直观显示支付方式
     */
    private String payTypeString;
    /**
     * 支付时间，记录订单支付完成的时间
     */
    private Date payTime;
    /**
     * 订单状态，表示订单当前的状态
     */
    private Byte orderStatus;
    /**
     * 订单状态字符串，用于直观显示订单状态
     */
    private String orderStatusString;
    /**
     * 用户地址，记录下单用户的配送地址
     */
    private String userAddress;
    /**
     * 昵称，下单用户的昵称
     */
    private String nickName;
    /**
     * 登录名，下单用户的登录用户名
     */
    private String loginName;
    /**
     * 创建时间，记录订单创建的时间
     */
    private Date createTime;
    /**
     * 订单种中商品信息，记录订单中包含的商品信息
     */
    private List<OrderItemVO> orderItemVOS;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayStatusString() {
        return payStatusString;
    }

    public void setPayStatusString(String payStatusString) {
        this.payStatusString = payStatusString;
    }

    public String getPayTypeString() {
        return payTypeString;
    }

    public void setPayTypeString(String payTypeString) {
        this.payTypeString = payTypeString;
    }

    public String getOrderStatusString() {
        return orderStatusString;
    }

    public void setOrderStatusString(String orderStatusString) {
        this.orderStatusString = orderStatusString;
    }

    public List<OrderItemVO> getOrderItemVOS() {
        return orderItemVOS;
    }

    public void setOrderItemVOS(List<OrderItemVO> orderItemVOS) {
        this.orderItemVOS = orderItemVOS;
    }
}
