package com.example.chickensoup.service;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;

public interface OptionService {
    Integer addOption(OptionDto OptionDto) throws ServiceException;

    String deleteOption(OptionDto OptionDto) throws ServiceException;

    String modifyOption(OptionDto OptionDto) throws ServiceException;

    OptionDto searchOption(String OptionName) throws ServiceException;
}
