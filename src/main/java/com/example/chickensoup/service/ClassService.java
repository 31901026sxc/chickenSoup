package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    Integer addClassInfo(ClassDto classDto) throws ServiceException;//指创建一个空班级

    Integer addClass(ClassDto classDto) throws ServiceException;

    String deleteClass(Integer classId) throws ServiceException;

    String modifyClassInfo(ClassDto classDto) throws ServiceException;//返回success或者报错

    String addAStudent(UserDto userDto, Integer classId) throws ServiceException;

    String addStudents(List<UserDto> userDtoList, Integer classId) throws ServiceException;

    String deleteAStudent(UserDto userDto, Integer classId);

    ClassDto searchClassById(Integer classId) throws ServiceException;

    List<ClassDto> searchClasses() throws ServiceException;//获取所有班级列表teacher,admin
}
