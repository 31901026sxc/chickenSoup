package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    @Override
    public Integer addTest(TestDto TestDto) throws ServiceException {
        return null;
    }

    @Override
    public String deleteTest(TestDto TestDto) throws ServiceException {
        return null;
    }

    @Override
    public String cancelTest(Integer testId) throws ServiceException {
        return null;
    }

    @Override
    public String modifyTest(TestDto TestDto) throws ServiceException {
        return null;
    }

    @Override
    public List<TestDto> searchTestByCreator(Integer creatorId) throws ServiceException {
        return null;
    }

    @Override
    public String endTest(Integer testId) throws ServiceException {
        return null;
    }
}
