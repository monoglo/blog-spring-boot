package com.rankofmatrix.blog.service.impl;

import org.jasypt.encryption.StringEncryptor;
import com.google.common.collect.Lists;
import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import com.rankofmatrix.blog.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAPIServiceImpl implements UserAPIService{

    private UserRepository userRepository;
    private StringEncryptor encryptor;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptor(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User registerUser(User registerUser) {
        if (userRepository.findByEmail(registerUser.getEmail()) == null) {
            User checkedUser = new User();
            checkedUser.setEmail(registerUser.getEmail());
            checkedUser.setPassword(encryptor.encrypt(registerUser.getPassword()));
            checkedUser.setNickname(registerUser.getNickname());
            return userRepository.save(checkedUser);
        } else {
            return null;
        }
    }

    @Override
    public User loginUser(User loginUser) {
        User checkedUser;
        checkedUser =  userRepository.findByEmail(loginUser.getEmail());
        if (checkedUser != null) {
            if (encryptor.decrypt(checkedUser.getPassword()).equals(loginUser.getPassword())) {
                return checkedUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
