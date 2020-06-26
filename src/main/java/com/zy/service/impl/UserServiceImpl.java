package com.zy.service.impl;

import com.zy.dao.UserRepository;
import com.zy.entity.User;
import com.zy.service.UserService;
import com.zy.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository repository;
    @Override
    public User checkUser(String username, String password) {

        User user = repository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
