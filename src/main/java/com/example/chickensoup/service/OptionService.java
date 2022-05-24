package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface OptionService {
    Integer addOption(OptionDto optionDto) throws ServiceException;

    String deleteOption(Integer OptionId) throws ServiceException;

    String modifyOption(OptionDto optionDto) throws ServiceException;

    Set<OptionDto> searchOptionByQuestion(Integer questionId) throws ServiceException;
}
