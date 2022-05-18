package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Integer addQuestion(QuestionDto questionDto) throws ServiceException {
        return null;
    }

    @Override
    public String deleteQuestion(Integer questionId) throws ServiceException {
        return null;
    }

    @Override
    public String modifyQuestion(QuestionDto questionDto) throws ServiceException {
        return null;
    }

    @Override
    public QuestionDto searchQuestion(Integer questionId) throws ServiceException {
        return null;
    }

    @Override
    public List<QuestionDto> searchQuestionByKeyword(String keyword) throws ServiceException {
        return null;
    }
}
