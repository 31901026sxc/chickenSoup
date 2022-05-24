package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.ClassEntity;
import com.example.chickensoup.entity.ClassUserLinkEntity;
import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.repository.ClassRepository;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer addClassInfo(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            BeanUtils.copyProperties(classDto, classEntity);
            return classRepository.save(classEntity).getId();
        }catch (Exception e){
            throw new ServiceException("添加班级出错！");
        }
    }

    @Override
    public Integer addClass(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            BeanUtils.copyProperties(classDto, classEntity);
            return classRepository.save(classEntity).getId();
        }catch (Exception e){
            throw new ServiceException("添加班级出错！");
        }
    }

    @Override
    public String deleteClass(Integer classId) throws ServiceException {
        try{
            ClassEntity classEntity = classRepository.findById(classId).get();
            classRepository.delete(classEntity);
            return "success";
        }catch (NoSuchElementException e){
            throw new ServiceException("该班级不存在！");
        }catch (Exception e){
            throw new ServiceException("添加班级出错！");
        }
    }

    @Override
    public String modifyClass(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            BeanUtils.copyProperties(classDto, classEntity);
            classRepository.save(classEntity);
            return "success";
        }catch (Exception e){
            throw new ServiceException("修改班级信息出错！");
        }
    }

    @Override
    public String addAStudent(UserDto userDto,Integer classId) throws ServiceException {
        if(!userRepository.findById(userDto.getId()).get().getId().equals(userDto.getId()))
            throw new ServiceException("该用户不存在！");
        try{
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDto, userEntity);
            ClassEntity classEntity = classRepository.findById(classId).get();
            ClassUserLinkEntity classUserLink = new ClassUserLinkEntity();
            Set<UserEntity> list = classEntity.getClassUserLinks();
        }catch (Exception e){
            throw new ServiceException("添加班级出错！");
        }
    }

    @Override
    public String addStudents(List<UserDto> userDtoList,Integer classId) throws ServiceException {


    }

    @Override
    public ClassDto searchClassById(Integer classId) throws ServiceException {
        ClassEntity classEntity = classRepository.findById(classId).get();
        ClassDto classDto = new ClassDto(classEntity.getId(),classEntity.getClassName(),classEntity.getClassMark(),
                classEntity.getClassUserLinks().stream().map(classUserLinks -> classUserLinks)));
    }

    @Override
    public List<ClassDto> searchClasses() throws ServiceException {
        List<ClassEntity> classEntity = classRepository.findAll();

    }
}
