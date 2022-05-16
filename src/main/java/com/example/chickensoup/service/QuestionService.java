package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.QuestionDto;

public interface QuestionService {
    Integer addQuestion(QuestionDto QuestionDto) throws ServiceException;

    String deleteQuestion(QuestionDto QuestionDto) throws ServiceException;

    String modifyQuestion(QuestionDto QuestionDto) throws ServiceException;

    QuestionDto searchQuestion(String QuestionName) throws ServiceException;
}
