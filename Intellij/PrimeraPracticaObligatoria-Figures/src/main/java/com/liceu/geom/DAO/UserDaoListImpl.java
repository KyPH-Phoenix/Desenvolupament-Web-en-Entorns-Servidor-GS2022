package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoListImpl implements UserDao {
    static List<User> userList = new ArrayList<>();

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUserList(){
        return userList;
    }
}
