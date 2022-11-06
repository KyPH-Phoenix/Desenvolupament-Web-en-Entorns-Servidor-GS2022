package com.liceu.geom.service;

import com.liceu.geom.DAO.UserDao;
import com.liceu.geom.DAO.UserDaoListImpl;
import com.liceu.geom.model.Figure;
import com.liceu.geom.model.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDaoListImpl();
    static int lastId = 1;

    public User createUser(String userName) {
        User user = new User();
        user.setId(lastId);
        user.setUserName(userName);
        lastId++;

        userDao.addUser(user);

        return user;
    }

    public User getUserById(int id) {
        List<User> userList = userDao.getUserList();

        return userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
