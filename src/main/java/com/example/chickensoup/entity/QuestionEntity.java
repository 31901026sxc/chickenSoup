package com.example.chickensoup.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer id;

    @Column(name = "question_content")
    private String questionContent;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "answer")
    private String answer;

    @OneToMany(mappedBy = "question")
    private Set<QuestionOptionLinkEntity> questionOptionLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<TestQuestionLinkEntity> testQuestionLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<AnswerSheetEntity> answerSheets = new LinkedHashSet<>();

    public Set<AnswerSheetEntity> getAnswerSheets() {
        return answerSheets;
    }

    public void setAnswerSheets(Set<AnswerSheetEntity> answerSheets) {
        this.answerSheets = answerSheets;
    }

    public Set<TestQuestionLinkEntity> getTestQuestionLinks() {
        return testQuestionLinks;
    }

    public void setTestQuestionLinks(Set<TestQuestionLinkEntity> testQuestionLinks) {
        this.testQuestionLinks = testQuestionLinks;
    }

    public Set<QuestionOptionLinkEntity> getQuestionOptionLinks() {
        return questionOptionLinks;
    }

    public void setQuestionOptionLinks(Set<QuestionOptionLinkEntity> questionOptionLinks) {
        this.questionOptionLinks = questionOptionLinks;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}