package com.wonders.core.service;

import com.wonders.core.base.BaseService;
import com.wonders.core.entity.User;
import com.wonders.core.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserMapper> {

    public User findUserByUsername(String username) {
        List<User> userList = this.mapper.findUserByUsername(username);
        if (userList == null || userList.size() == 0) return null;
        else return userList.get(0);

    }
}
