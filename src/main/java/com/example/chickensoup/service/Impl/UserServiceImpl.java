package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.Constants;
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
    public String deleteUser(Integer userId) throws ServiceException {
        //TODO
        return null;
    }

    @Override
    public String cancelUser(Integer userId) throws ServiceException {
        UserEntity user = userRepository.getById(userId);
        if (user.getUserType().equals(Constants.USER_CANCELLATION))
        {
            throw new ServiceException("该用户已注销");
        }
        user.setUserType(Constants.USER_CANCELLATION);
        userRepository.save(user);
        return "success";
    }

    @Override
    public String modifyUser(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public String modifyUserType(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public UserDto searchUser(Integer userId) throws ServiceException {
        //记得把密码去掉
        return null;
    }

    @Override
    public UserDto adminSearchUser(Integer userId) throws ServiceException {
        //显示所有信息
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
