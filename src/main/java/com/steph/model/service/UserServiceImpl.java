package com.steph.model.service;

import com.steph.entity.UserDetails;
import com.steph.model.persistence.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsDao userDetailsDao;

    @Override
    public UserDetails loginCheck(String username, String password) {
        return userDetailsDao.getUserDetailsByUsernameAndPassword(username, password);
    }

    @Override
    public UserDetails createUser(UserDetails newUser) {
        if (userDetailsDao.findById(newUser.getUsername()).orElse(null) == null) {
            UserDetails user = userDetailsDao.save(newUser);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userDetailsDao.findById(username).orElse(null);
    }
}
