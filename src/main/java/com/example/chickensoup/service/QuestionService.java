package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuestionService {
    Integer addQuestion(QuestionDto questionDto) throws ServiceException;

    String deleteQuestion(QuestionDto questionDto) throws ServiceException;

    String modifyQuestion(QuestionDto questionDto) throws ServiceException;

    QuestionDto searchQuestion(Integer questionId) throws ServiceException;

    List<QuestionDto> searchQuestionByKeyword(String keyword) throws ServiceException;
}
