package com.example.chickensoup.service;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Integer addUser(UserDto userDto) throws ServiceException;

    String deleteUser(Integer userId) throws ServiceException;//从数据库里面删除admin（不可以自己删自己）

    String cancelUser(Integer userId) throws ServiceException;//注销用户admin

    String modifyUser(UserDto userDto) throws ServiceException;//拒绝改变用户类型

    String modifyUserType(UserDto userDto) throws ServiceException;//可改变用户类型

    UserDto searchUser(String userName) throws ServiceException;//teacher,admin

    UserEntity login(Integer userId, String password) throws ServiceException;
}
