package com.liceu.ExempleSpring.DAO;

import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public String getSomething() {
        return "Something";
    }
}
