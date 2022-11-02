package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoListImpl implements UserDao {
    static List<User> userList = new ArrayList<>();
    static int lastId = 1;

    @Override
    public User addUser(String userName) {
        User user = new User();
        user.setId(lastId);
        user.setUserName(userName);

        lastId++;

        userList.add(user);

        return user;
    }

    @Override
    public User getUser() {
        return null;
    }
}
