package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import com.rankofmatrix.blog.service.UserAPIService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserAPIServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserAPIServiceImpl userAPIServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUid(2);
        user.setEmail("150@163.com");
        user.setPassword("password");
        user.setNickname("hw");

        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> finalList = userAPIServiceImpl.getAllUsers();

        System.out.println(finalList);
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
    }
}