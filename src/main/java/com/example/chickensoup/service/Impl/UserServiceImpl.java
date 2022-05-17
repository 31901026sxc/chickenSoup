package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.ExceptionMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Integer addUser(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public String deleteUser(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public String cancelUser(Integer userId) throws ServiceException {
        return null;
    }

    @Override
    public String modifyUser(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public UserDto searchUser(String userName) throws ServiceException {
        return null;
    }

    @Override
    public UserEntity login(Integer userId, String password) {
        UserEntity user = userRepository.getById(userId);
        if (user==null)
            throw new RuntimeException("没有该用户");
        if (!user.getUserPassword().equals(password))
            throw new RuntimeException("密码错误");
        return user;
    }
}