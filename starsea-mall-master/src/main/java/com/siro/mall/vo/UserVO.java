package com.siro.mall.vo;

import java.io.Serializable;

/**
 * @author shan
 * @date 2025-03-23
 */
public class UserVO implements Serializable {
    private Long userId;
    private String nickName;
    private String loginName;
    private String address;
    private String avatar;
    private int shopCartItemCount;
    private Integer balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getShopCartItemCount() {
        return shopCartItemCount;
    }

    public Integer getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setShopCartItemCount(int shopCartItemCount) {
        this.shopCartItemCount = shopCartItemCount;
    }
}
