package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.OptionEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.repository.OptionRepository;
import com.example.chickensoup.service.OptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Integer addOption(OptionDto optionDto) throws ServiceException {
        try{
            OptionEntity optionEntity = new OptionEntity();
            optionEntity.setOptionContent(optionDto.getOptionContent());
            optionEntity.setQuestionId(optionDto.getQuestionId());
            return optionRepository.save(optionEntity).getId();
        }catch (Exception e){
            throw new ServiceException("添加问题选项出错");
        }
    }

    @Override
    public String deleteOption(Integer OptionId) throws ServiceException {
        try{
            OptionEntity optionEntity = optionRepository.findById(OptionId).get();
            optionRepository.delete(optionEntity);
        }catch (NoSuchElementException e)
        {
            throw new ServiceException("没有这个选项");
        }
        catch (Exception e){
            throw new ServiceException(e.toString());
        }
        return "success";
    }

    @Override
    public String modifyOption(OptionDto optionDto) throws ServiceException {
        try{
            OptionEntity optionEntity = new OptionEntity();
            optionEntity.setOptionContent(optionDto.getOptionContent());
            optionEntity.setQuestionId(optionDto.getQuestionId());
            optionEntity.setId(optionDto.getId());
            optionRepository.save(optionEntity);
        }catch (Exception e){
            throw new ServiceException("修改问题选项出错");
        }
        return "success";
    }

    @Override
    public Set<OptionDto> searchOptionByQuestion(Integer questionId) throws ServiceException {
        try{
            Set<OptionDto> optionDtos = new HashSet<>();
            optionRepository.findAllByQuestionId(questionId).stream().forEach(option ->
                optionDtos.add(new OptionDto(option.getId(),option.getQuestionId(),option.getOptionContent()))
            );
            return optionDtos;
        }catch (Exception e){
            throw new ServiceException("查询问题选项出错");
        }
    }
}
