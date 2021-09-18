package com.rankofmatrix.blog.conf;

import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringEncryptor encryptor;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userRepository.findByEmail(token.getUsername());

        if(user != null){
            user.setPassword(encryptor.decrypt(user.getPassword()));
            return new SimpleAuthenticationInfo(user,user.getPassword(), getName());
        }
        return null;
    }
}
