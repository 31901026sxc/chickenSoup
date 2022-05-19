package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    @Override
    public TestDto addTest(TestDto TestDto) throws ServiceException {
        //如果考试题目跟库中题目不完全相同以库中题目为准（id作为辨认符），返回的TestDto.testDescription要写上这个警告
        return null;
    }

    @Override
    public String deleteTest(Integer testId) throws ServiceException {
        return null;
    }

    @Override
    public String cancelTest(Integer testId) throws ServiceException {
        return null;
    }

    @Override
    public TestDto modifyTest(TestDto TestDto) throws ServiceException {
        //正在进行中和已完成的考试无法修改,creatorId无法更改
        return null;
    }

    @Override
    public String modifyTestAdmin(TestDto TestDto) throws ServiceException {
        //啥都能改,但是题目冲突依然以库中为准
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
