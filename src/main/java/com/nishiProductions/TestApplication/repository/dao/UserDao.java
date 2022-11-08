package com.nishiProductions.TestApplication.repository.dao;

import com.nishiProductions.TestApplication.model.entity.User;
import com.nishiProductions.TestApplication.repository.dto.UserDto;

import java.util.List;

public interface UserDao extends BaseDao<User>{

    List<UserDto> getAllUsers();
    UserDto saveUser(UserDto userDto);
    User findById(Long userId);
    UserDto updateUser(UserDto userDto);
}
