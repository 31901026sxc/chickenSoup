package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClassService {
    Integer addClassInfo(ClassDto classDto) throws ServiceException;//指创建一个空班级

    String deleteClass(Integer classId) throws ServiceException;

    String modifyClass(ClassDto classDto) throws ServiceException;//返回success或者报错

    String addAStudent(UserDto userDto) throws ServiceException;

    String addStudents(List<UserDto> userDtoList) throws ServiceException;

    ClassDto searchClassByName(String className) throws ServiceException;

    ClassDto searchClassById(Integer classId) throws ServiceException;
}
