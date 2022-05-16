package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;

public interface ClassService {
    Integer addClass(ClassDto ClassDto) throws ServiceException;

    String deleteClass(ClassDto ClassDto) throws ServiceException;

    String modifyClass(ClassDto ClassDto) throws ServiceException;

    ClassDto searchClass(String ClassName) throws ServiceException;
}
