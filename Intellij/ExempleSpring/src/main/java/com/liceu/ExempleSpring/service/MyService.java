package com.liceu.ExempleSpring.service;

import com.liceu.ExempleSpring.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired
    UserDAO userDao;

    public String getSomething(){
        return userDao.getSomething();
    }

    public int addNumbers(int num1, int num2) {
        return num1 + num2;
    }
}
