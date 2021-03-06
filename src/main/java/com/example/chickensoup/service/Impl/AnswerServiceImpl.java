package com.example.chickensoup.service.Impl;

import com.example.chickensoup.entity.AnswerSheetContentLinkEntity;
import com.example.chickensoup.entity.AnswerSheetEntity;
import com.example.chickensoup.entity.TestEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.AnswerSheetContentLinkDto;
import com.example.chickensoup.form.AnswerSheetDto;
import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.repository.*;
import com.example.chickensoup.service.AnswerService;
import com.example.chickensoup.utils.Constants;
import org.apache.catalina.User;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerSheetRepository answerSheetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerSheetContentLinkRepository answerSheetContentLinkRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Integer submitAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        try {
            if (!(answerSheetRepository.findByUserIdAndAndTestId(answerSheetDto.getUserId(), answerSheetDto.getTestId()) == null))
                throw new ServiceException("你已经提交过啦");
            AnswerSheetEntity answerSheet = new AnswerSheetEntity();
            double score = autoCorrectAnswerSheet(answerSheetDto);
            answerSheet.setScore((int) score);
            answerSheet.setUser(userRepository.getById(answerSheetDto.getUserId()));
            answerSheet.setUploadTime(answerSheetDto.getUploadTime());
            answerSheet.setTest(testRepository.getById(answerSheetDto.getTestId()));
            System.out.println(score);
            Integer answerSheetId = answerSheetRepository.save(answerSheet).getId();
            System.out.println(answerSheetId);
            answerSheetDto.getAnswerSheetContentLinks().forEach(link -> {
                AnswerSheetContentLinkEntity temp = new AnswerSheetContentLinkEntity();
                temp.setAnswerContent(link.getAnswerContent());
                temp.setAnswerSheet(answerSheetRepository.getById(answerSheetId));
                temp.setQuestion(questionRepository.getById(link.getQuestion().getId()));
                answerSheetContentLinkRepository.save(temp);
            });

            answerSheet.setScore(Integer.valueOf(-1));
            return answerSheetRepository.save(answerSheet).getId();
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public double autoCorrectAnswerSheet(AnswerSheetDto answerSheetDto) throws ServiceException {
        try {
            if (answerSheetDto.getScore() >= 0)
                return answerSheetDto.getScore();
            double score = 0;
            boolean flag = false;
            for (AnswerSheetContentLinkDto link : answerSheetDto.getAnswerSheetContentLinks()
            ) {

                if (link.getQuestion().getQuestionType().equals(Constants.Q_CATEGORY_SUBJECTIVE))
                    flag = true;
                if (link.getQuestion().getAnswer().equals(link.getAnswerContent())) {
                    score += link.getQuestion().getScore();
                }
                if (flag)
                    score = -score;
                return score;
            }
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

        return 0;
    }

    @Override
    public AnswerSheetDto searchAnswerSheetById(Integer answerSheetId) throws ServiceException {
        try {
            AnswerSheetEntity answerSheet = answerSheetRepository.findById(answerSheetId).get();
            AnswerSheetDto answerSheetDto = new AnswerSheetDto(
                    answerSheet.getId(), answerSheet.getUser().getId(), answerSheet.getTest().getId()
                    , answerSheet.getUploadTime(), answerSheet.getScore(),
                    answerSheet.getAnswerSheetContentLinks().stream().map(answerSheetContentLinks -> new AnswerSheetContentLinkDto(
                            answerSheetContentLinks.getId(), answerSheetContentLinks.getAnswerSheet().getId(),
                            answerSheetContentLinks.getAnswerContent(),
                            new QuestionDto(answerSheetContentLinks.getQuestion().getId(), answerSheetContentLinks.getQuestion().getQuestionContent(),
                                    answerSheetContentLinks.getQuestion().getQuestionType(), answerSheetContentLinks.getQuestion().getAnswer(),
                                    answerSheetContentLinks.getQuestion().getScore(),
                                    answerSheetContentLinks.getQuestion().getOptions().stream().map(option ->
                                            new OptionDto(option.getId(), option.getQuestionId(), option.getOptionContent())
                                    ).collect(Collectors.toSet())
                            )
                    )).collect(Collectors.toSet())
            );
            return answerSheetDto;
        } catch (NoSuchElementException e) {
            throw new ServiceException("未找到对应id的答卷");
        } catch (Exception e) {
            throw new ServiceException("根据id查找答卷错误");
        }

    }

    @Override
    public AnswerSheetDto searchAnswerSheetByStudentAndTest(Integer studentId, Integer testId) throws ServiceException {
        try {
            AnswerSheetEntity answerSheet = answerSheetRepository.findByUserIdAndAndTestId(studentId, testId);
            AnswerSheetDto answerSheetDto = new AnswerSheetDto(
                    answerSheet.getId(), answerSheet.getUser().getId(), answerSheet.getTest().getId()
                    , answerSheet.getUploadTime(), answerSheet.getScore(),
                    answerSheet.getAnswerSheetContentLinks().stream().map(answerSheetContentLinks -> new AnswerSheetContentLinkDto(
                            answerSheetContentLinks.getId(), answerSheetContentLinks.getAnswerSheet().getId(),
                            answerSheetContentLinks.getAnswerContent(),
                            new QuestionDto(answerSheetContentLinks.getQuestion().getId(), answerSheetContentLinks.getQuestion().getQuestionContent(),
                                    answerSheetContentLinks.getQuestion().getQuestionType(), answerSheetContentLinks.getQuestion().getAnswer(),
                                    answerSheetContentLinks.getQuestion().getScore(),
                                    answerSheetContentLinks.getQuestion().getOptions().stream().map(option ->
                                            new OptionDto(option.getId(), option.getQuestionId(), option.getOptionContent())
                                    ).collect(Collectors.toSet())
                            )
                    )).collect(Collectors.toSet())
            );
            return answerSheetDto;
        } catch (IllegalArgumentException e) {
            throw new ServiceException("该查询条件不存在");
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }

    @Override
    public List<AnswerSheetDto> searchAllAnswerSheets(Integer testId) throws ServiceException {
        try {
            answerSheetRepository.findByTest(testId).forEach(e -> System.out.println(e.getId()));
            List<AnswerSheetDto> list = new ArrayList<AnswerSheetDto>();
            Set<OptionDto> options = new HashSet<>();

            answerSheetRepository.findByTest(testId).forEach(answerSheet -> {
                AnswerSheetDto answerSheetDto = new AnswerSheetDto(
                        answerSheet.getId(), answerSheet.getUser().getId(), answerSheet.getTest().getId()
                        , answerSheet.getUploadTime(), answerSheet.getScore(),
                        answerSheet.getAnswerSheetContentLinks().stream().map(answerSheetContentLinks -> new AnswerSheetContentLinkDto(
                                answerSheetContentLinks.getId(), answerSheetContentLinks.getAnswerSheet().getId(),
                                answerSheetContentLinks.getAnswerContent(),
                                new QuestionDto(answerSheetContentLinks.getQuestion().getId(), answerSheetContentLinks.getQuestion().getQuestionContent(),
                                        answerSheetContentLinks.getQuestion().getQuestionType(), answerSheetContentLinks.getQuestion().getAnswer(),
                                        answerSheetContentLinks.getQuestion().getScore(),
                                        answerSheetContentLinks.getQuestion().getOptions().stream().map(option ->
                                                new OptionDto(option.getId(), option.getQuestionId(), option.getOptionContent())
                                        ).collect(Collectors.toSet())
                                )
                        )).collect(Collectors.toSet())
                );
                list.add(answerSheetDto);
            });
            return list;
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

    }


    @Override
    public List<AnswerSheetDto> searchAllStudentSheets(Integer studentId) throws ServiceException {
        try {
            answerSheetRepository.findByUserId(studentId).forEach(e -> System.out.println(e.getId()));
            List<AnswerSheetDto> list = new ArrayList<AnswerSheetDto>();

            answerSheetRepository.findByUserId(studentId).forEach(answerSheet -> {
                AnswerSheetDto answerSheetDto = new AnswerSheetDto(
                        answerSheet.getId(), answerSheet.getUser().getId(), answerSheet.getTest().getId()
                        , answerSheet.getUploadTime(), answerSheet.getScore(),
                        answerSheet.getAnswerSheetContentLinks().stream().map(answerSheetContentLinks -> new AnswerSheetContentLinkDto(
                                answerSheetContentLinks.getId(), answerSheetContentLinks.getAnswerSheet().getId(),
                                answerSheetContentLinks.getAnswerContent(),
                                new QuestionDto(answerSheetContentLinks.getQuestion().getId(), answerSheetContentLinks.getQuestion().getQuestionContent(),
                                        answerSheetContentLinks.getQuestion().getQuestionType(), answerSheetContentLinks.getQuestion().getAnswer(),
                                        answerSheetContentLinks.getQuestion().getScore(),
                                        answerSheetContentLinks.getQuestion().getOptions().stream().map(option ->
                                                new OptionDto(option.getId(), option.getQuestionId(), option.getOptionContent())
                                        ).collect(Collectors.toSet())
                                )
                        )).collect(Collectors.toSet())
                );
                System.out.println(answerSheetDto.getUserId());
                list.add(answerSheetDto);
            });
            return list;
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }
    }

    @Override
    public DefaultCategoryDataset createDataset(Integer studentId) throws ServiceException {
        AnswerServiceImpl answerService;
        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
        List<AnswerSheetDto> answerSheetDtos = searchAllStudentSheets(studentId);
        // 横轴名称(列名称)
        ArrayList<String> tests = new ArrayList<>();
        ArrayList<Double> grades = new ArrayList<>();
        for (AnswerSheetDto dto : answerSheetDtos
        ) {
            System.out.println(dto.getId());
            linedataset.addValue(Double.valueOf(dto.getScore()), studentId, dto.getTestId());
        }

        return linedataset;
    }
}
