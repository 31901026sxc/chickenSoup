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

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Integer addOption(OptionDto optionDto) throws ServiceException {
        try{
            OptionEntity optionEntity = new OptionEntity();
            BeanUtils.copyProperties(optionEntity,optionDto);
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
        }catch (Exception e){
            throw new ServiceException("删除问题选项出错");
        }
        return "success";
    }

    @Override
    public String modifyOption(OptionDto optionDto) throws ServiceException {
        try{
            OptionEntity optionEntity = new OptionEntity();
            BeanUtils.copyProperties(optionEntity,optionDto);
            optionRepository.save(optionEntity).getId();
        }catch (Exception e){
            throw new ServiceException("修改问题选项出错");
        }
        return "success";
    }

    @Override
    public List<OptionDto> searchOptionByQuestion(Integer questionId) throws ServiceException {
        try{
            List<OptionDto> optionDtos = new ArrayList<OptionDto>();
            optionRepository.findByQuestionId(questionId).stream().map(option ->
                    optionDtos.add(new OptionDto(option.getId(),option.getQuestionId(),option.getOptionContent()))
            );
            return optionDtos;
        }catch (Exception e){
            throw new ServiceException("删除问题选项出错");
        }
    }
}
