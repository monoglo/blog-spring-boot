package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.exception.PasswordDoesNotMatchException;
import com.rankofmatrix.blog.exception.UserDoesNotExistException;
import org.jasypt.encryption.StringEncryptor;
import com.google.common.collect.Lists;
import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import com.rankofmatrix.blog.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
            // 为避免用户自行设置生成信息，进行过滤
            checkedUser.setEmail(registerUser.getEmail());
            checkedUser.setPassword(encryptor.encrypt(registerUser.getPassword()));
            checkedUser.setNickname(registerUser.getNickname());
            checkedUser.setAvatarUrl(registerUser.getAvatarUrl());
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
                checkedUser.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
                User resultUser = userRepository.save(checkedUser);
                resultUser.setPassword("");
                return resultUser;
            } else {
                throw new PasswordDoesNotMatchException();
            }
        } else {
            throw new UserDoesNotExistException();
        }
    }

    @Override
    public User findUserByUid(Integer uid) throws UserDoesNotExistException{
        User resultUser = userRepository.findByUid(uid);
        if (resultUser != null) {
            return resultUser;
        } else {
            throw new UserDoesNotExistException();
        }
    }

    @Override
    public Boolean isLoginUserLegal(User checkedUser) {
        return checkedUser.getEmail() != null && checkedUser.getPassword() != null;
    }

}
