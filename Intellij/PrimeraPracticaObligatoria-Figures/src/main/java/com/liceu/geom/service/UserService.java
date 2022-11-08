package com.liceu.geom.service;

import com.liceu.geom.DAO.UserDao;
import com.liceu.geom.DAO.UserDaoListImpl;
import com.liceu.geom.model.Figure;
import com.liceu.geom.model.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDaoListImpl();

    public User createUser(String userName) {
        User user = new User();
        user.setUserName(userName);

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
