package com.siro.mall.dao;

import com.siro.mall.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface AdminUserMapper {

    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}
