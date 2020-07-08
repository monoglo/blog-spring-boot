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

    // 用户信息是否可以进行登陆
    Boolean isLoginUserLegal(User checkedUser);
}
