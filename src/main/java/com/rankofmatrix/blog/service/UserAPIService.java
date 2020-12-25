package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.User;

import java.util.List;

public interface UserAPIService {
    // 获取所有用户
    List<User> getAllUsers();
    // 用户注册
    User registerUser(User registerUser);
    // 用户登录
    User loginUser(User loginUser);
    User fastLogin();
    // 获取某一指定UID的用户
    User findUserByUid(Integer uid);
    // 用户信息是否可以进行登陆
    Boolean isLoginUserLegal(User checkedUser);
}
