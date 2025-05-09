package com.siro.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * @author shan
 * @date 2025-03-23
 */
@ApiModel(value = "首页配置项类")
public class IndexConfig {
    /**
     * 配置项标识，用于唯一标识一个配置信息
     */
    private Long configId;

    /**
     * 配置名称，描述配置项的名称
     */
    private String configName;

    /**
     * 配置类型，表示配置项的类别
     */
    private Byte configType;

    /**
     * 商品标识，关联到特定商品的配置
     */
    private Long goodsId;

    /**
     * 重定向URL，用户操作后跳转的页面地址
     */
    private String redirectUrl;

    /**
     * 配置排序，用于展示配置项时的排序依据
     */
    private Integer configRank;

    /**
     * 删除标记，表示配置项是否已被删除
     */
    private Byte isDeleted;

    /**
     * 创建时间，记录配置项被创建的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建用户，记录创建配置项的用户标识
     */
    private Integer createUser;

    /**
     * 更新时间，记录配置项最后一次被更新的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 更新用户，记录最后一次更新配置项的用户标识
     */
    private Integer updateUser;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public Byte getConfigType() {
        return configType;
    }

    public void setConfigType(Byte configType) {
        this.configType = configType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public Integer getConfigRank() {
        return configRank;
    }

    public void setConfigRank(Integer configRank) {
        this.configRank = configRank;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", configId=").append(configId);
        sb.append(", configName=").append(configName);
        sb.append(", configType=").append(configType);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", redirectUrl=").append(redirectUrl);
        sb.append(", configRank=").append(configRank);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append("]");
        return sb.toString();
    }

}
