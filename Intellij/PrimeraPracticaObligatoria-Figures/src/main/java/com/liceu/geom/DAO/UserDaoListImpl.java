package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public User getUserById(int id) {
        return userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null)
        ;
    }
}
