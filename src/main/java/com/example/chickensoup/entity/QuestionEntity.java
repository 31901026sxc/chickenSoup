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

    @Column(name = "score")
    private Integer score;

    @OneToMany(mappedBy = "question")
    private Set<AnswerSheetContentLinkEntity> answerSheetContentLinks = new LinkedHashSet<>();

    public Set<AnswerSheetContentLinkEntity> getAnswerSheetContentLinks() {
        return answerSheetContentLinks;
    }

    public void setAnswerSheetContentLinks(Set<AnswerSheetContentLinkEntity> answerSheetContentLinks) {
        this.answerSheetContentLinks = answerSheetContentLinks;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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