package com.example.forum.dao;

import com.example.forum.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByEmailLike(String email);
}