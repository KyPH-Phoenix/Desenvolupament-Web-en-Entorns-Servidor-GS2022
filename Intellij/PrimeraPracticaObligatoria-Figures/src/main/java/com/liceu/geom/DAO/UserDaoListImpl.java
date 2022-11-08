package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoListImpl implements UserDao {
    static List<User> userList = new ArrayList<>();
    static int lastId = 1;

    @Override
    public synchronized void addUser(User user) {
        user.setId(lastId);
        lastId++;

        userList.add(user);
    }

    @Override
    public List<User> getUserList(){
        return userList;
    }
}
