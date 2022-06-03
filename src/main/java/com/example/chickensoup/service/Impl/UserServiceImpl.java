package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer addUser(UserDto userDto) throws ServiceException {
        try {
            UserEntity user = new UserEntity();
            user.setUserType(userDto.getUserType());
            user.setUserName(userDto.getUserName());
            user.setDepartment(userDto.getDepartment());
            user.setUserPassword(userDto.getUserPassword());
            System.out.println(user);
            return userRepository.save(user).getId();
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public String deleteUser(Integer userId) throws ServiceException {
        try {
            UserEntity user = userRepository.findById(userId).get();
            if (user.getUserType().equals(Constants.USER_ADMIN)) {
                return "failed,你想的美";
            }
            userRepository.delete(user);
            return "success";
        } catch (NoSuchElementException e) {
            throw new ServiceException("该用户不存在！");
        } catch (Exception e) {
            throw new ServiceException("删除用户错误！");
        }
    }

    @Override
    public String cancelUser(Integer userId) throws ServiceException {
        UserEntity user = userRepository.getById(userId);
        if (user.getUserType().equals(Constants.USER_CANCELLATION)) {
            throw new ServiceException("该用户已注销");
        }
        user.setUserType(Constants.USER_CANCELLATION);
        userRepository.save(user);
        return "success";
    }

    @Override
    public String modifyUser(UserDto userDto) throws ServiceException {
        try {
            UserEntity user = userRepository.getById(userDto.getId());
            if (!user.getUserType().equals(userDto.getUserType()))
                throw new ServiceException("你无法在这里修改用户权限");
            BeanUtils.copyProperties(userDto, user);//√
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            throw new ServiceException("修改用户信息（不包含类型）出错！" + e.getMessage());
        }
    }

    @Override
    public String modifyUserType(UserDto userDto) throws ServiceException {
        try {
            UserEntity user = new UserEntity();
            BeanUtils.copyProperties(userDto, user);
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            throw new ServiceException("修改用户类型出错！");
        }
    }

    @Override
    public UserDto searchUser(Integer userId) throws ServiceException {
        try {
            UserEntity user = userRepository.getById(userId);
            UserDto userDto = new UserDto(user.getId(), user.getUserName(), user.getDepartment(),
                    user.getUserType(), "******");
            return userDto;
        } catch (Exception e) {
            throw new ServiceException("查找用户失败！");
        }
    }

    @Override
    public UserDto adminSearchUser(Integer userId) throws ServiceException {
        try {
            UserEntity user = userRepository.getById(userId);
            UserDto userDto = new UserDto(user.getId(), user.getUserName(), user.getDepartment(),
                    user.getUserType(), user.getUserPassword());
            return userDto;
        } catch (Exception e) {
            throw new ServiceException("管理员查找用户失败！");
        }
    }

    @Override
    public UserEntity login(Integer userId, String password) {
        UserEntity user = userRepository.getById(userId);
        if (user == null)
            throw new RuntimeException("没有该用户");
        if (user.getUserType().equals(Constants.USER_CANCELLATION))
            throw new RuntimeException("该用户已注销");
        if (!user.getUserPassword().equals(password))
            throw new RuntimeException("密码错误");
        return user;
    }
}
