package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;

public interface UserService {
    Integer addUser(UserDto userDto) throws ServiceException;

    String deleteUser(UserDto userDto) throws ServiceException;

    String modifyUser(UserDto userDto) throws ServiceException;

    UserDto searchUser(String userName) throws ServiceException;
}
