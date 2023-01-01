package com.steph.model.service;

import com.steph.entity.UserDetails;

public interface UserService {

    UserDetails loginCheck(String username, String password);

    UserDetails createUser(UserDetails newUser);

    UserDetails findByUsername(String username);

}
