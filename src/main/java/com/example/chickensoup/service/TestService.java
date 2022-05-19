package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TestService {//仅老师和管理员可以使用这个服务
    TestDto addTest(TestDto TestDto) throws ServiceException;//如果考试题目跟库中题目不完全相同以库中题目为准（id作为辨认符）

    String deleteTest(Integer TestId) throws ServiceException;//这个是从数据库里面删除（admin）

    String cancelTest(Integer testId) throws ServiceException;//这个是把考试设置成取消状态（正在进行中和已完成的考试不得取消）admin

    TestDto modifyTest(TestDto TestDto) throws ServiceException;//修改考试信息

    String modifyTestAdmin(TestDto TestDto) throws ServiceException;//你啥都能改admin

    List<TestDto> searchTestByCreator(Integer creatorId) throws ServiceException;

    String endTest(Integer testId) throws ServiceException;//立即停止考试（要求该考试在进行中）admin
}
