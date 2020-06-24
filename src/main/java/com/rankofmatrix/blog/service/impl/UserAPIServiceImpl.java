package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import com.rankofmatrix.blog.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAPIServiceImpl implements UserAPIService{

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(String nickname, String password, String email, String avatarUrl) {
        User register_user = new User();
        register_user.setNickname(nickname);
        register_user.setPassword(password);
        register_user.setEmail(email);
        register_user.setAvatarUrl(avatarUrl);
        userRepository.save(register_user);
        return register_user;
    }

    @Override
    public User loginUser(User login_user) {
        return userRepository.findByEmailAndPassword(login_user.getEmail(), login_user.getPassword());
    }

}
