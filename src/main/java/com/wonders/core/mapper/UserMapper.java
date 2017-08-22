package com.wonders.core.mapper;

import com.wonders.core.base.MapperExtension;
import com.wonders.core.entity.User;

import java.util.List;

public interface UserMapper extends MapperExtension<User> {

    List<User> findUserByUsername(String username);
}