package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuestionService {//teacher,admin
    Integer addQuestion(QuestionDto questionDto) throws ServiceException;

    //不得删除题目

    String modifyQuestion(QuestionDto questionDto) throws ServiceException;

    QuestionDto searchQuestion(Integer questionId) throws ServiceException;

    List<QuestionDto> searchQuestionByKeyword(String keyword) throws ServiceException;//根据关键字查询相关问题
}
