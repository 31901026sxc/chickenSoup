package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.QuestionEntity;
import com.example.chickensoup.entity.TestEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.repository.TestRepository;
import com.example.chickensoup.service.TestService;
import com.example.chickensoup.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionServiceImpl questionService;
    @Override
    public TestDto addTest(TestDto TestDto) throws ServiceException {
        try {
            TestEntity test = new TestEntity();
            Set<QuestionDto> questions= new HashSet<>();
            questions = TestDto.getTestQuestionLinks();
            BeanUtils.copyProperties(TestDto,test);
            testRepository.save(test);
            return TestDto;
        }catch(Exception e)
        {
            throw new ServiceException(e.toString());
        }


    }

    @Override
    public String deleteTest(Integer testId) throws ServiceException {
        try{
            TestEntity test = new TestEntity();
            test = testRepository.getById(testId);
            if (test.getTestStatus().equals(Constants.TEST_STATUS_TESTING)||test.getTestStatus().equals(Constants.TEST_STATUS_END))
                throw new ServiceException("删除失败，考试在进行中或者已结束");
            testRepository.delete(test);
            return "success";
        }catch (Exception e)
        {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public String cancelTest(Integer testId) throws ServiceException {
        try{
            TestEntity test = new TestEntity();
            test = testRepository.getById(testId);
            if (test.getTestStatus().equals(Constants.TEST_STATUS_TESTING)||test.getTestStatus().equals(Constants.TEST_STATUS_END))
                throw new ServiceException("取消失败，考试在进行中或者已结束");
            test.setTestStatus(Constants.USER_CANCELLATION);
            test.setTestEndTime(Instant.ofEpochSecond(System.currentTimeMillis()));
            test.setTestStartTime(Instant.ofEpochSecond(System.currentTimeMillis()));
            testRepository.save(test);
            return "success";
        }catch (Exception e)
        {
            throw new ServiceException(e.toString());
        }
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
        try{
            TestEntity test = new TestEntity();
            test = testRepository.getById(testId);
            if (!test.getTestStatus().equals(Constants.TEST_STATUS_TESTING))
                throw new ServiceException("结束失败，考试没有在进行中");
            test.setTestStatus(Constants.TEST_STATUS_END);
            test.setTestEndTime(Instant.ofEpochSecond(System.currentTimeMillis()));
            testRepository.save(test);
            return "success";
        }catch (Exception e)
        {
            throw new ServiceException(e.toString());
        }
    }
}
