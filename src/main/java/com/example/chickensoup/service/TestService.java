package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;

public interface TestService {
    Integer addTest(TestDto TestDto) throws ServiceException;

    String deleteTest(TestDto TestDto) throws ServiceException;

    String modifyTest(TestDto TestDto) throws ServiceException;

    TestDto searchTest(String TestName) throws ServiceException;
}
