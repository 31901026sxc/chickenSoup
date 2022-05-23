package com.example.chickensoup.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer_sheet_content_link")
public class AnswerSheetContentLinkEntity {
    @Id
    @Column(name = "link_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_sheet_id")
    private AnswerSheetEntity answerSheet;

    @Column(name = "answer_content")
    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_sheet_id",insertable = false,updatable = false)
    private AnswerSheetEntity answerSheetEntity;

    public AnswerSheetEntity getAnswerSheetEntity() {
        return answerSheet;
    }

    public void setAnswerSheetEntity(AnswerSheetEntity answerSheetEntity) {
        this.answerSheet = answerSheet;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public AnswerSheetEntity getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(AnswerSheetEntity answerSheet) {
        this.answerSheet = answerSheet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}