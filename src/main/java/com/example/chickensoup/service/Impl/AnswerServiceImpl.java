package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.AnswerSheetEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.AnswerSheetContentLinkDto;
import com.example.chickensoup.form.AnswerSheetDto;
import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.repository.AnswerSheetRepository;
import com.example.chickensoup.service.AnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerSheetRepository answerSheetRepository;

    @Override
    public Integer submitAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        AnswerSheetEntity answerSheet = new AnswerSheetEntity();
        BeanUtils.copyProperties(answerSheetDto,answerSheet);
        answerSheet.setScore(Integer.valueOf(-1));
        return answerSheetRepository.save(answerSheet).getId();
    }

    @Override
    public double autoCorrectAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        //TODO
        return 0;
    }

    @Override
    public AnswerSheetDto searchAnswerSheetById(Integer answerSheetId) throws ServiceException {
        try{
            AnswerSheetEntity answerSheet = answerSheetRepository.findById(answerSheetId).get();
            AnswerSheetDto answerSheetDto = new AnswerSheetDto();
            BeanUtils.copyProperties(answerSheet,answerSheetDto);
            return answerSheetDto;
        }catch (NullPointerException e){
             throw new ServiceException("未找到对应id的答题表");
        }

    }

    @Override
    public AnswerSheetDto searchAnswerSheetByStudentAndTest(Integer studentId, Integer testId) throws ServiceException {
        AnswerSheetEntity answerSheet = answerSheetRepository.findByUserAndTest(studentId,testId);
        AnswerSheetDto answerSheetDto = new AnswerSheetDto();
        BeanUtils.copyProperties(answerSheet,answerSheetDto);
        return answerSheetDto;
    }

    @Override
    public List<AnswerSheetDto> searchAllAnswerSheets(Integer testId) throws ServiceException {
        //TODO 查看是否意义正确,QuestionDto与QuestionEntity冲突
        List<AnswerSheetDto> list = new ArrayList<AnswerSheetDto>();
        answerSheetRepository.findByTest(testId).forEach(answerSheet ->{
            AnswerSheetDto answerSheetDto = new AnswerSheetDto(
                answerSheet.getId(),answerSheet.getUser().getId(),answerSheet.getTest().getId()
                ,answerSheet.getUploadTime(), answerSheet.getScore(),
                answerSheet.getAnswerSheetContentLinks().stream().map(answerSheetContentLinks -> new AnswerSheetContentLinkDto(
                    answerSheetContentLinks.getId(),answerSheetContentLinks.getAnswerSheet().getId(),
                    answerSheetContentLinks.getAnswerContent(),
                    new QuestionDto(answerSheetContentLinks.getQuestion().getId(),answerSheetContentLinks.getQuestion().getQuestionContent()
                        answerSheetContentLinks.getQuestion().getQuestionType(),answerSheetContentLinks.getQuestion().getAnswer(),
                        answerSheetContentLinks.getQuestion().getScore(),
                        answerSheetContentLinks.getQuestion().getQuestionOptionLinks()
                    )
                ))
            );
            list.add(answerSheetDto);
        });
        return null;
    }

    @Override
    public List<AnswerSheetDto> searchAllStudentSheets(Integer studentId) throws ServiceException {
        //TODO 同上

        return null;
    }
}
