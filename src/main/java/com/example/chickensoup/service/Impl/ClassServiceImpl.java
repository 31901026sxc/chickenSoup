package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;

import java.util.List;

public class ClassServiceImpl implements ClassService {
    @Override
    public Integer addClassInfo(ClassDto classDto) throws ServiceException {
        return null;
    }

    @Override
    public String deleteClass(Integer classId) throws ServiceException {
        return null;
    }

    @Override
    public String modifyClass(ClassDto classDto) throws ServiceException {
        return null;
    }

    @Override
    public String addAStudent(UserDto userDto) throws ServiceException {
        return null;
    }

    @Override
    public String addStudents(List<UserDto> userDtoList) throws ServiceException {
        return null;
    }

    @Override
    public ClassDto searchClassById(Integer classId) throws ServiceException {
        return null;
    }

    @Override
    public List<ClassDto> searchClasses() throws ServiceException {
        return null;
    }
}
