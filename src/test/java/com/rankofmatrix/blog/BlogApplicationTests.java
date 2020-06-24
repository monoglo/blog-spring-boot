package com.rankofmatrix.blog;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.controller.UserController;
import com.rankofmatrix.blog.service.UserAPIService;
import com.rankofmatrix.blog.service.impl.UserAPIServiceImpl;
import com.rankofmatrix.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void testUserEqualsFunction() {
        System.out.println("Testing UserEqualsFunction...");
        User u1 = new User();
        User u2 = new User();
        u1.setNickname("asd");
        u1.setEmail("asd");
        // u1.setPassword("asd");
        u2.setNickname("asd");
        u2.setEmail("asd");
        u2.setPassword("asd");

//        if (u1.equals(u2)) {
//            System.out.println("u1 Equals u2.");
//        } else {
//            System.out.println("u1 is NOT Equals u2.");
//        }

        System.out.println(u1.toString());
    }

}
