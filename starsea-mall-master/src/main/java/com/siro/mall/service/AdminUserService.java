package com.siro.mall.service;

import com.siro.mall.entity.AdminUser;

/**
 * @author shan
 * @date 2025-03-23
 */
public interface AdminUserService {
    //管理员用户登录
    AdminUser login(String userName, String password);

    //获取用户信息
    AdminUser getUserDetailById(Integer loginUserId);

    //修改当前登录用户的密码
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    //修改当前登录用户的名称信息
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
