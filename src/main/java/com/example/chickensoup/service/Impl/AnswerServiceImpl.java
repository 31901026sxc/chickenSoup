package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.AnswerSheetDto;
import com.example.chickensoup.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {
    @Override
    public Integer submitAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        //TODO
        return null;
    }

    @Override
    public double autoCorrectAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        //TODO
        return 0;
    }

    @Override
    public AnswerSheetDto searchAnswerSheetById(Integer answerSheetId) throws ServiceException {
        //TODO
        return null;
    }

    @Override
    public AnswerSheetDto searchAnswerSheetByStudent(Integer studentId, Integer testId) throws ServiceException {
        //TODO
        return null;
    }

    @Override
    public List<AnswerSheetDto> searchAllAnswerSheets(Integer testId) throws ServiceException {
        //TODO
        return null;
    }

    @Override
    public List<AnswerSheetDto> searchAStudentSheets(Integer studentId) throws ServiceException {
        //TODO
        return null;
    }
}
