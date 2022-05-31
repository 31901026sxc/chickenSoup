package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.AnswerSheetDto;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AnswerService {
    Integer submitAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException;//提交答卷初始化分数为-1

    double autoCorrectAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException;//自动批改答卷（仅限全客观题的试卷）

    AnswerSheetDto searchAnswerSheetById(Integer answerSheetId) throws ServiceException;//根据id来查找答卷

    AnswerSheetDto searchAnswerSheetByStudentAndTest(Integer studentId,Integer testId) throws ServiceException;//根据学生和考试来查找答卷

    List<AnswerSheetDto> searchAllAnswerSheets(Integer testId) throws ServiceException;//teacher，admin

    List<AnswerSheetDto> searchAllStudentSheets(Integer studentId) throws ServiceException;

    DefaultCategoryDataset createDataset(Integer studentId) throws ServiceException;
}
