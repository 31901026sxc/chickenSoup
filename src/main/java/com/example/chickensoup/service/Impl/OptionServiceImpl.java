package com.example.chickensoup.service.Impl;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.service.OptionService;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {
    @Override
    public Integer addOption(OptionDto optionDto) throws ServiceException {
        return null;
    }

    @Override
    public String deleteOption(Integer OptionId) throws ServiceException {
        return null;
    }

    @Override
    public String modifyOption(OptionDto optionDto) throws ServiceException {
        return null;
    }

    @Override
    public OptionDto searchOptionByQuestion(Integer questionId) throws ServiceException {
        return null;
    }
}
