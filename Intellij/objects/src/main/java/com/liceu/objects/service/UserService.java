package com.liceu.objects.service;

import com.liceu.objects.dao.UserDAO;
import com.liceu.objects.exception.DistinctPasswordException;
import com.liceu.objects.exception.UsernameAlreadyExistsException;
import com.liceu.objects.model.User;
import com.liceu.objects.util.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void registerUser(String username, String password, String rPassword, String firstname,
                             String lastname, String email) {
        if (!password.equals(rPassword)) throw new DistinctPasswordException();
        if (usernameAlreadyExists(username)) throw new UsernameAlreadyExistsException();

        User user = new User();
        user.setUsername(username);
        user.setPassword(Utilities.getSHA512(password));
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        userDAO.createUser(user);
    }

    private boolean usernameAlreadyExists(String username) {
        List<User> users = userDAO.getAll();

        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }
}
