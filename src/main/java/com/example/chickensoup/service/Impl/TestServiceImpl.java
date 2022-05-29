package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.*;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.*;
import com.example.chickensoup.repository.*;
import com.example.chickensoup.service.TestService;
import com.example.chickensoup.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private TestQuestionLinkRepository testQuestionLinkRepository;
    @Autowired
    private UserTestLinkRepository userTestLinkRepository;
    @Override
    public TestDto addTest(TestSeedDto testSeed) throws ServiceException {
        try {
            UserEntity creator = userRepository.findById(testSeed.getCreatorId()).get();
            if (creator.getUserType().equals(Constants.USER_STUDENT))
                throw new ServiceException("学生没有权限来创建试卷");
            if (testSeed.getTestEndTime().isAfter(Instant.ofEpochSecond(System.currentTimeMillis()))||
                testSeed.getTestEndTime().isAfter(testSeed.getTestStartTime()))
                throw new ServiceException("试卷结束时间非法");
            Set<QuestionEntity> questionEntities = new HashSet<>();
            Set<QuestionDto> questionDtos = new HashSet<>();
            TestEntity test = new TestEntity();
            test.setTestEndTime(testSeed.getTestEndTime());
            test.setTestStatus(testSeed.getTestStatus());
            test.setTestStartTime(testSeed.getTestStartTime());
            test.setTestDescription(testSeed.getTestDescription());
            test.setCreatorId(testSeed.getCreatorId());
            test.setTestCreateTime(testSeed.getTestCreateTime());
            testSeed.getQuestions().forEach(id ->
                    questionEntities.add(questionRepository.findById(id).get()));
            test = testRepository.save(test);

            testSeed.getQuestions().forEach(question ->{
                questionDtos.add(questionService.searchQuestion(question));
            });


            TestDto result = new TestDto(test.getId(),test.getTestCreateTime(),test.getTestStartTime(),
                    test.getTestEndTime(),test.getTestStatus(),test.getCreatorId(),test.getTestDescription(),
                        questionDtos);
            return result;
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
    public List<TestDto> searchTestByCreator(Integer creatorId) throws ServiceException {
        try{
            List<TestEntity> entities= testRepository.findAllByCreatorId(creatorId);
            for (TestEntity t:entities
                 ) {
                t.setTestQuestionLinks(testQuestionLinkRepository.findAllByTestId(t.getId()));
            }
            List<TestDto> result = new ArrayList<>();
            for (TestEntity entity:entities
                 ) {
                System.out.println(entity.getTestQuestionLinks().size());
                Set<QuestionDto> questionDtos= new HashSet<>();
                for (TestQuestionLinkEntity link:entity.getTestQuestionLinks()
                     ) {
                    questionDtos.add(questionService.searchQuestion(link.getQuestion().getId()));
                }
                TestDto t = new TestDto(entity.getId(),entity.getTestCreateTime(),entity.getTestStartTime(),
                        entity.getTestEndTime(),entity.getTestStatus(),entity.getCreatorId(),
                        entity.getTestDescription(),questionDtos);
                result.add(t);
            }
            return result;
        }catch (Exception e){
            throw new ServiceException(e.toString());
        }
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

    @Override
    public String addStudentsToTest(List<Integer> students,Integer testId) {
        try{
            TestEntity test= testRepository.getById(testId);
            for (Integer id:students
                 ) {
                UserEntity user = userRepository.findById(id).get();
                if (user.getUserType().equals(Constants.USER_CANCELLATION))
                    throw new RuntimeException("用户id为:"+id+"的用户已注销");
                UserTestLinkEntity temp = new UserTestLinkEntity();
                temp.setTest(test);
                temp.setUser(user);
                temp.setUserType(user.getUserType());
                userTestLinkRepository.save(temp);
            }
        }catch (Exception e){
            throw new ServiceException(e.toString());
        }

        return null;
    }
}
