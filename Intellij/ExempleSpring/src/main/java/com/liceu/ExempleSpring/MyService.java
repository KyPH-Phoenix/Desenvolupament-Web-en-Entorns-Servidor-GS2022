package com.liceu.ExempleSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {
    @Autowired
    UserDAO userDao;
    public String getSomething(){
        return userDao.getSomething();
    }
}
