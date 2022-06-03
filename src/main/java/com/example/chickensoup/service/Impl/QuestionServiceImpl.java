package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.OptionEntity;
import com.example.chickensoup.entity.QuestionEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.repository.AnswerSheetRepository;
import com.example.chickensoup.repository.OptionRepository;
import com.example.chickensoup.repository.QuestionRepository;
import com.example.chickensoup.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private OptionServiceImpl optionService;

    @Override
    public Integer addQuestion(QuestionDto questionDto) throws ServiceException {
        try {
            QuestionEntity question = new QuestionEntity();
            question.setQuestionType(question.getQuestionType());
            question.setQuestionContent(question.getQuestionContent());
            question.setAnswer(question.getAnswer());
            question.setScore(question.getScore());
            int id = questionRepository.save(question).getId();
            for (OptionDto option : questionDto.getQuestionOptionLinks()
            ) {
                OptionEntity optionEntity = new OptionEntity();
                optionEntity.setId(option.getId());
                optionEntity.setOptionContent(option.getOptionContent());
                optionEntity.setQuestionId(option.getQuestionId());
                optionEntity.setQuestionId(id);
                optionRepository.save(optionEntity);
            }
            return id;
        } catch (Exception e) {
            throw new ServiceException("添加问题失败");
        }

    }

    @Override
    public String modifyQuestion(QuestionDto questionDto) throws ServiceException {
        try {
            QuestionEntity question = new QuestionEntity();
            question.setId(question.getId());
            question.setQuestionType(question.getQuestionType());
            question.setQuestionContent(question.getQuestionContent());
            question.setAnswer(question.getAnswer());
            question.setScore(question.getScore());
            int id = questionRepository.save(question).getId();
            for (OptionDto option : questionDto.getQuestionOptionLinks()
            ) {
                OptionEntity optionEntity = new OptionEntity();
                optionEntity.setId(option.getId());
                optionEntity.setOptionContent(option.getOptionContent());
                optionEntity.setQuestionId(option.getQuestionId());
                optionEntity.setQuestionId(id);
                optionRepository.save(optionEntity);
            }
            return "success";
        } catch (Exception e) {
            throw new ServiceException("修改问题出错");
        }
    }

    @Override
    public QuestionDto searchQuestion(Integer questionId) throws ServiceException {
        try {
            Set<OptionDto> optionDtos = new HashSet<>();
            optionRepository.findAllByQuestionId(questionId).stream().map(option ->
                    optionDtos.add(new OptionDto(option.getId(), option.getQuestionId(), option.getOptionContent()))
            ).collect(Collectors.toSet());
            QuestionEntity question = questionRepository.findById(questionId).get();
            QuestionDto questionDto = new QuestionDto(question.getId(), question.getQuestionContent(),
                    question.getQuestionType(), question.getAnswer(), question.getScore(),
                    optionDtos);
            return questionDto;
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public List<QuestionDto> searchQuestionByKeyword(String keyword) throws ServiceException {
        try {
            List<QuestionDto> questions = new ArrayList<>();
            List<QuestionEntity> entities = new ArrayList<>();
            entities = questionRepository.findAllByQuestionContentLike(keyword);
            for (QuestionEntity questionEntity : entities
            ) {
                questions.add(new QuestionDto(questionEntity.getId(), questionEntity.getQuestionContent(),
                        questionEntity.getQuestionType(), questionEntity.getAnswer(), questionEntity.getScore(),
                        optionService.searchOptionByQuestion(questionEntity.getId())));
            }
            return questions;
        } catch (Exception e) {
            throw new ServiceException("查询失败");
        }
    }
}
