package com.rankofmatrix.blog;

import static org.assertj.core.api.Assertions.assertThat;

import com.rankofmatrix.blog.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGettingAllUsers() throws Exception {
         System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/users/", String.class));
    }
}
