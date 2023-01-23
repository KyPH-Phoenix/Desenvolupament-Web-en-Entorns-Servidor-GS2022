package com.liceu.objects.dao;

import com.liceu.objects.model.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    List<User> getAll();
    User getByUsername(String username, String password);
}
