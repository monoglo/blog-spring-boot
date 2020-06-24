package com.rankofmatrix.blog.repository;

import org.springframework.data.repository.CrudRepository;
import com.rankofmatrix.blog.model.User;

public interface  UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String Password);
}
