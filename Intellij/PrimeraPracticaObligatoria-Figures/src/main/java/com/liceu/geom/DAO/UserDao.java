package com.liceu.geom.DAO;

import com.liceu.geom.model.User;

public interface UserDao {
    User addUser(String userName);
    User getUser();
}
