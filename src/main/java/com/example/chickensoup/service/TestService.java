package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TestService {//仅老师和管理员可以使用这个服务
    Integer addTest(TestDto TestDto) throws ServiceException;

    String deleteTest(TestDto TestDto) throws ServiceException;//这个是从数据库里面删除（admin）

    String cancelTest(Integer testId) throws ServiceException;//这个是把考试设置成取消状态（正在进行中和已完成的考试不得取消）admin

    String modifyTest(TestDto TestDto) throws ServiceException;//修改考试信息

    List<TestDto> searchTestByCreator(Integer creatorId) throws ServiceException;

    String endTest(Integer testId) throws ServiceException;//立即停止考试（要求该考试在进行中）admin
}
