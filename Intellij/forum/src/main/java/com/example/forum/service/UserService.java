package com.example.forum.service;

import com.example.forum.dao.UserDao;
import com.example.forum.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findByEmailLike(String email) {
        return userDao.findByEmailLike(email);
    }

    public void save(User user) {
        userDao.save(user);
    }
}
