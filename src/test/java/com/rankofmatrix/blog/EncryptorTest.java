package com.rankofmatrix.blog;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EncryptorTest {
    // 注入StringEncryptor
    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encry() {
        // 加密数据库用户名：testusername
        String username = encryptor.encrypt("RankOfMatrix");
        System.out.println(username);

        // 加密数据库密码：testpassword
        String password = encryptor.encrypt("ying8rui");
        System.out.println(password);
    }
}
