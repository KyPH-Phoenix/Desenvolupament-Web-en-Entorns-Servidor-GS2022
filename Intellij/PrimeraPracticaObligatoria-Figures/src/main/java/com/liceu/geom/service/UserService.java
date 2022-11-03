package com.liceu.geom.service;

import com.liceu.geom.DAO.UserDao;
import com.liceu.geom.DAO.UserDaoListImpl;
import com.liceu.geom.model.User;

public class UserService {
    UserDao userDao = new UserDaoListImpl();

    public User createUser(String userName) {
        return userDao.addUser(userName);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
