package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;

public interface UserAPIService {
    // 获取所有用户
    public Iterable<User> getAllUsers();
    // 用户注册
    public User registerUser(String nickname, String password, String email, String avatarUrl);
    // 用户登录
    public User loginUser(User login_user);
}
