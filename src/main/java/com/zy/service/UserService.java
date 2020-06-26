package com.zy.service;

import com.zy.entity.User;

public interface UserService {
    User checkUser(String username,String password);
}
