package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.exception.UserDoesNotExistException;
import com.rankofmatrix.blog.repository.UserRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private StringEncryptor encryptor;
    BCryptPasswordEncoder encoder = passwordEncoder();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptor(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.rankofmatrix.blog.model.User user = userRepository.findByEmail(email);
        if (user != null) {
            System.out.println(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), encoder.encode(encryptor.decrypt(user.getPassword())), AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        } else {
            throw new UserDoesNotExistException();
        }
    }
}
