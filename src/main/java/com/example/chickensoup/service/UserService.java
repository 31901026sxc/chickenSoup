package com.example.chickensoup.service;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Integer addUser(UserDto userDto) throws ServiceException;

    String deleteUser(Integer userId) throws ServiceException;//从数据库里面删除

    String cancelUser(Integer userId) throws ServiceException;//注销用户

    String modifyUser(UserDto userDto) throws ServiceException;//不成功就throw exception

    UserDto searchUser(String userName) throws ServiceException;

    UserEntity login(Integer userId, String password) throws ServiceException;
}
