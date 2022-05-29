package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.ClassEntity;
import com.example.chickensoup.entity.ClassUserLinkEntity;
import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.repository.ClassRepository;
import com.example.chickensoup.repository.ClassUserLinkRepository;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private ClassUserLinkRepository classUserLinkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer addClassInfo(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassMark(classDto.getClassMark());
            classEntity.setClassName(classDto.getClassName());
            return classRepository.save(classEntity).getId();
        }catch (Exception e){
            throw new ServiceException("添加班级出错！"+e);
        }
    }

    @Override
    public Integer addClass(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassMark(classDto.getClassMark());
            classEntity.setClassName(classDto.getClassName());
            Set<UserEntity> users= new HashSet<>();
            for (UserDto user:classDto.getClassUserLinks()
                 ) {
                UserEntity temp = userRepository.getById(user.getId());
                if (temp.getUserType().equals(Constants.USER_CANCELLATION))
                    throw new ServiceException("id为"+temp.getId()+"的学生已注销");
                users.add(temp);
            }
            return classRepository.save(classEntity).getId();
        }catch (NoSuchElementException e)
        {
            throw new ServiceException("有一个学生id异常"+e.toString());
        }
        catch (Exception e){
            throw new ServiceException("添加班级出错！"+e);
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
            throw new ServiceException("删除班级出错！"+e);
        }
    }

    @Override
    public String modifyClassInfo(ClassDto classDto) throws ServiceException {
        try{
            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassName(classDto.getClassName());
            classEntity.setClassMark(classDto.getClassMark());
            classEntity.setId(classDto.getId());
            classRepository.save(classEntity);
            return "success";
        }catch (Exception e){
            throw new ServiceException("修改班级信息出错！"+e);
        }
    }

    @Override
    public String addAStudent(UserDto userDto,Integer classId) throws ServiceException {
        if(!userRepository.findById(userDto.getId()).get().getId().equals(userDto.getId()))
            throw new ServiceException("该用户不存在！");
        try{
            UserEntity userEntity = userRepository.getById(userDto.getId());
            if (userEntity.getUserType().equals(Constants.USER_CANCELLATION))
                throw new ServiceException("该学生id已注销");
            ClassEntity classEntity = classRepository.findById(classId).get();
            ClassUserLinkEntity classUserLink = new ClassUserLinkEntity();
            classUserLink.set_class(classEntity);
            classUserLink.setUser(userEntity);
            classUserLinkRepository.save(classUserLink);
            return "success";
        }catch (NoSuchElementException e)
        {
            throw new ServiceException("找不到改学生的id");
        }
        catch (Exception e){
            throw new ServiceException("添加学生出错！"+e.toString());
        }
    }

    @Override
    public String addStudents(List<UserDto> userDtoList,Integer classId) throws ServiceException {
        for (UserDto dto:userDtoList
             ) {
            if(!userRepository.findById(dto.getId()).get().getId().equals(dto.getId()))
                throw new ServiceException("用户id为"+dto.getId()+"的用户信息错误");
        }
        try{
            for (UserDto dto:userDtoList
                 ) {
                addAStudent(dto,classId);
            }
            return "success";
        }catch (Exception e){
            throw new ServiceException(e.toString());
        }
    }

    @Override
    public String deleteAStudent(UserDto userDto, Integer classId) {
        if(!userRepository.findById(userDto.getId()).get().getId().equals(userDto.getId()))
            throw new ServiceException("该用户不存在！");
        try{
            UserEntity userEntity = userRepository.getById(userDto.getId());
            ClassEntity classEntity = classRepository.findById(classId).get();
            ClassUserLinkEntity classUserLink = new ClassUserLinkEntity();
            classUserLink.set_class(classEntity);
            classUserLink.setUser(userEntity);
            classUserLinkRepository.delete(classUserLink);
            return "success";
        }catch (NoSuchElementException e)
        {
            throw new ServiceException("找不到信息"+e);
        }
        catch (Exception e){
            throw new ServiceException("删除学生出错！");
        }
    }

    @Override
    public ClassDto searchClassById(Integer classId) throws ServiceException {
        try{
            ClassEntity classEntity = classRepository.findById(classId).get();
            Set<UserDto> users =new HashSet<>();
            Set<ClassUserLinkEntity> links = classEntity.getClassUserLinks();
            for (ClassUserLinkEntity entity:links
            ) {
                users.add(new UserDto(entity.getId(),entity.getUser().getUserName(),
                        entity.getUser().getDepartment(),entity.getUser().getUserType(),"******"));
            }
            ClassDto classDto = new ClassDto(classEntity.getId(),classEntity.getClassName(),
                    classEntity.getClassMark(),users);
            return classDto;
        }catch (Exception e)
        {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public List<ClassDto> searchClasses() throws ServiceException {
        try{
            List<ClassEntity> classEntities = classRepository.findAll();
            List<ClassDto> classDtos = new ArrayList<>();
            for (ClassEntity entity :classEntities
            ) {
                classDtos.add(searchClassById(entity.getId()));
            }
            return classDtos;
        }
        catch (Exception e){
            throw new ServiceException(e.toString());
        }
    }
}
