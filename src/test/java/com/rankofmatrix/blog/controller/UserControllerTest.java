package com.rankofmatrix.blog.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void tearDown() {
        System.out.println("Test done");
    }

    @Test
    public void testGettingAllUsers() {
        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/users/", String.class));
    }

    @Test
    void registerUser() {
    }

    @Test
    void getAllUsers() {

    }

    @Test
    void loginUser() {
    }
}