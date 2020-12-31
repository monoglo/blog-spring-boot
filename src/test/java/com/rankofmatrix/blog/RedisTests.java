package com.rankofmatrix.blog;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTests {

    private RedisTemplate<String, Object> redisTemplate;
    private UserRepository userRepository;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void redisWriteAndRead(){
        User user = userRepository.findByUid(2);
        assert redisTemplate != null;
        redisTemplate.opsForValue().set(user.getUid().toString(), user);
        System.out.println(redisTemplate.opsForValue().get(user.getUid().toString()));
    }
}
