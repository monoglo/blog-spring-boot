package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;

import java.util.List;

public interface UserAPIService {
    // 获取所有用户
    List<User> getAllUsers();
    // 用户注册
    User registerUser(User registerUser);
    // 用户登录
    User loginUser(User loginUser);
}
