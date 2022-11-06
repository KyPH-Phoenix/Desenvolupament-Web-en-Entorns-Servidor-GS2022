package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getUserList();
}
