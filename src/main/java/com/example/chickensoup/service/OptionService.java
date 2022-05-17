package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;
import org.springframework.stereotype.Service;

@Service
public interface OptionService {
    Integer addOption(OptionDto optionDto) throws ServiceException;

    String deleteOption(OptionDto optionDto) throws ServiceException;

    String modifyOption(OptionDto optionDto) throws ServiceException;

    OptionDto searchOptionByQuestion(Integer questionId) throws ServiceException;
}
