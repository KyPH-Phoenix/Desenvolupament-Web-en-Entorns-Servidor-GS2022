package com.example.forum.dao;

import com.example.forum.model.User;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByEmailLike(String email);

    @Modifying
    @Query("update User u set u.name = :name, u.email = :email where u.id = :id")
    void updateUser(@Param("id") long id, @Param("name") String name, @Param("email") String email);
}