package com.siro.mall.service.impl;

import com.siro.mall.common.Constants;
import com.siro.mall.common.ServiceResultEnum;
import com.siro.mall.dao.UserMapper;
import com.siro.mall.entity.User;
import com.siro.mall.service.UserService;
import com.siro.mall.utils.*;
import com.siro.mall.vo.UserVO;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author shan
 * @date 2025-03-23
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageResult getUsersPage(PageQueryUtil pageUtil) {
        List<User> mallUsers = userMapper.findUserList(pageUtil);
        int total = userMapper.getTotalUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (userMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        registerUser.setBalance(0);
        if (userMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        User user = userMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(user, userVO);
            //设置购物车中的数量
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, userVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public UserVO updateUserInfo(User mallUser, HttpSession httpSession) {
        // 从HTTP会话中获取当前用户信息
        UserVO userTemp = (UserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        // 根据用户ID从数据库中查询用户信息
        User userFromDB = userMapper.selectByPrimaryKey(userTemp.getUserId());
        // 检查用户信息是否存在于数据库中
        if (userFromDB != null) {
            // 如果昵称不为空，则更新用户的昵称
            if (!StringUtils.isEmpty(mallUser.getNickName())) {
                userFromDB.setNickName(MallUtils.cleanString(mallUser.getNickName()));
            }
            // 如果地址不为空，则更新用户的地址
            if (!StringUtils.isEmpty(mallUser.getAddress())) {
                userFromDB.setAddress(MallUtils.cleanString(mallUser.getAddress()));
            }
            // 如果头像不为空，则更新用户的头像
            if(!StringUtils.isEmpty(mallUser.getAvatar())){
                userFromDB.setAvatar(MallUtils.cleanString(mallUser.getAvatar()));
            }
            // 如果登录名不为空，则更新用户的登录名
            if(!StringUtils.isEmpty(mallUser.getLoginName())){
                userFromDB.setLoginName(MallUtils.cleanString(mallUser.getLoginName()));
            }
            // 更新用户信息到数据库
            if (userMapper.updateByPrimaryKeySelective(userFromDB) > 0) {
                // 创建一个新的用户视图对象
                UserVO newBeeMallUserVO = new UserVO();
                // 重新从数据库中获取更新后的用户信息
                userFromDB = userMapper.selectByPrimaryKey(mallUser.getUserId());
                // 将更新后的用户信息复制到视图对象中
                BeanUtil.copyProperties(userFromDB, newBeeMallUserVO);
                // 将更新后的用户视图对象存入HTTP会话中
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
                // 返回更新后的用户视图对象
                return newBeeMallUserVO;
            }
        }
        // 如果用户信息更新失败或者用户不存在，则返回null
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return userMapper.lockUserBatch(ids, lockStatus) > 0;
    }

    @Override
    public UserVO updateUserBalance(User mallUser, HttpSession httpSession) {
        // 从HTTP会话中获取当前用户信息
        UserVO userTemp = (UserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        // 根据用户ID从数据库中查询用户信息
        User userFromDB = userMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB != null){
                userFromDB.setBalance(mallUser.getBalance());
            // 更新用户信息到数据库
            if (userMapper.updateUserBalance(userFromDB) > 0) {
                // 创建一个新的用户视图对象
                UserVO newBeeMallUserVO = new UserVO();
                // 重新从数据库中获取更新后的用户信息
                userFromDB = userMapper.selectByPrimaryKey(mallUser.getUserId());
                // 将更新后的用户信息复制到视图对象中
                BeanUtil.copyProperties(userFromDB, newBeeMallUserVO);
                // 将更新后的用户视图对象存入HTTP会话中
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
                // 返回更新后的用户视图对象
                return newBeeMallUserVO;
            }
        }
        return null;
    }

    @Override
    public UserVO reductionUserBalance(User mallUser, HttpSession httpSession) {
        // 从HTTP会话中获取当前用户信息
        UserVO userTemp = (UserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        // 根据用户ID从数据库中查询用户信息
        User userFromDB = userMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB.getBalance() < mallUser.getBalance()){
            return null;
        }
        if (userFromDB != null){
            userFromDB.setBalance(mallUser.getBalance());
            // 更新用户信息到数据库
            if (userMapper.reductionUserBalance(userFromDB) > 0) {
                // 创建一个新的用户视图对象
                UserVO newBeeMallUserVO = new UserVO();
                // 重新从数据库中获取更新后的用户信息
                userFromDB = userMapper.selectByPrimaryKey(mallUser.getUserId());
                // 将更新后的用户信息复制到视图对象中
                BeanUtil.copyProperties(userFromDB, newBeeMallUserVO);
                // 将更新后的用户视图对象存入HTTP会话中
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
                // 返回更新后的用户视图对象
                return newBeeMallUserVO;
            }
        }
        return null;
    }

}
